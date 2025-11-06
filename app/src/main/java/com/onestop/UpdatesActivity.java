package com.onestop;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import androidx.core.content.FileProvider;


public class UpdatesActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private static final String LATEST_RELEASE_API = "https://api.github.com/repos/RiftWalker0/OneStop/releases/latest";

    private void applyTheme() {
        int mode = Prefs.getTheme(this);
        switch (mode) {
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
            nav.setCheckedItem(R.id.nav_updates);
            nav.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (drawer != null) drawer.closeDrawers();
                if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
                else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
                else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
                else if (id == R.id.nav_updates) {/* current */}
                else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
                return true;
            });
        }

        MaterialToolbar topBar = findViewById(R.id.top_bar);
        if (topBar != null) {
            topBar.setTitle(R.string.title_updates);
            topBar.setNavigationOnClickListener(v -> {
                if (drawer != null) {
                    drawer.openDrawer(Gravity.START);
                }
            });
        }

        Button btn = findViewById(R.id.btn_download_latest);
        if (btn != null) {
            btn.setOnClickListener(v -> downloadLatestRelease(btn));
        }
    }

    private void downloadLatestRelease(Button trigger) {
        trigger.setEnabled(false);
        Toast.makeText(this, R.string.download_latest_starting, Toast.LENGTH_SHORT).show();
        new Thread(() -> {
            try {
                JSONObject latest = fetchLatestRelease();
                if (latest == null) throw new IOException("No release information found");
                ReleaseAsset asset = findApkAsset(latest);
                if (asset == null) throw new IOException(getString(R.string.download_latest_no_asset));
                File apkFile = downloadAsset(asset);
                if (apkFile == null) throw new IOException(getString(R.string.download_latest_failed));
                runOnUiThread(() -> promptInstall(apkFile));
            } catch (IOException | JSONException e) {
                runOnUiThread(() -> {
                    String message = e.getMessage();
                    if (message == null || message.trim().isEmpty()) {
                        message = e.getClass().getSimpleName();
                    }
                    Toast.makeText(this, getString(R.string.download_latest_error, message), Toast.LENGTH_LONG).show();
                    trigger.setEnabled(true);
                });
                return;
            }
            runOnUiThread(() -> trigger.setEnabled(true));
        }).start();
    }

    private JSONObject fetchLatestRelease() throws IOException, JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(LATEST_RELEASE_API);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(20000);
            connection.setRequestProperty("Accept", "application/vnd.github+json");
            connection.setRequestProperty("User-Agent", "OneStop-Android");

            int code = connection.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new IOException("GitHub API responded with code " + code);
            }

            try (InputStream is = new BufferedInputStream(connection.getInputStream())) {
                byte[] buffer = new byte[4096];
                int read;
                StringBuilder builder = new StringBuilder();
                while ((read = is.read(buffer)) != -1) {
                    builder.append(new String(buffer, 0, read, StandardCharsets.UTF_8));
                }
                return new JSONObject(builder.toString());
            }
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    private ReleaseAsset findApkAsset(JSONObject release) throws JSONException {
        JSONArray assets = release.optJSONArray("assets");
        if (assets == null) return null;
        for (int i = 0; i < assets.length(); i++) {
            JSONObject asset = assets.getJSONObject(i);
            String name = asset.optString("name", "");
            String url = asset.optString("browser_download_url", "");
            if (name.toLowerCase().endsWith(".apk") && !url.isEmpty()) {
                return new ReleaseAsset(name, url);
            }
        }
        return null;
    }

    private File downloadAsset(ReleaseAsset asset) throws IOException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(asset.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Accept", "application/octet-stream");
            connection.setRequestProperty("User-Agent", "OneStop-Android");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(20000);

            int code = connection.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new IOException("Download failed with code " + code);
            }

            File downloadsDir = getExternalFilesDir(null);
            if (downloadsDir == null) {
                downloadsDir = getFilesDir();
            }
            if (!downloadsDir.exists() && !downloadsDir.mkdirs()) {
                throw new IOException("Unable to create download directory");
            }
            File apkFile = new File(downloadsDir, asset.name);
            if (apkFile.exists() && !apkFile.delete()) {
                throw new IOException("Unable to overwrite existing APK");
            }

            try (InputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream out = new FileOutputStream(apkFile)) {
                byte[] buffer = new byte[8192];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                out.flush();
            }
            return apkFile;
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    private void promptInstall(File apkFile) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean canInstall = getPackageManager().canRequestPackageInstalls();
            if (!canInstall) {
                new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.download_latest_permission_title)
                    .setMessage(R.string.download_latest_permission_message)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                            Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
                return;
            }
        }

        Uri apkUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", apkFile);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(install);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.download_latest_no_installer, Toast.LENGTH_LONG).show();
        }
    }

    private static class ReleaseAsset {
        final String name;
        final String url;

        ReleaseAsset(String name, String url) {
            this.name = name;
            this.url = url;
        }
    }
}
