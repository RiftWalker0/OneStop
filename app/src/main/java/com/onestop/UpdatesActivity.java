package com.onestop;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import java.io.File;

public class UpdatesActivity extends BaseActivity {
    private DrawerLayout drawer;
    private long downloadId = -1;
    private final BroadcastReceiver receiver = new BroadcastReceiver(){
        @Override public void onReceive(Context context, Intent intent){
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id == downloadId){
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                Cursor c = dm.query(new DownloadManager.Query().setFilterById(id));
                if (c != null && c.moveToFirst()){
                    int status = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_SUCCESSFUL){
                        String uriStr = c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
                        if (uriStr != null){
                            Uri fileUri = Uri.parse(uriStr);
                            File file = new File(fileUri.getPath());
                            Uri contentUri = FileProvider.getUriForFile(UpdatesActivity.this, getPackageName()+".provider", file);
                            Intent install = new Intent(Intent.ACTION_VIEW);
                            install.setDataAndType(contentUri, "application/vnd.android.package-archive");
                            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            startActivity(install);
                        }
                    }
                }
            }
        }
    };
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(this::onNav);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.updates);
        tb.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        tb.setNavigationOnClickListener(v -> drawer.open());
        getLayoutInflater().inflate(R.layout.content_updates, findViewById(R.id.content_frame), true);

        Button btnGit = findViewById(R.id.btn_github);
        Button btnDl = findViewById(R.id.btn_download);
        btnGit.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/RiftWalker0/OneStop/releases/latest")));
        });
        btnDl.setOnClickListener(v -> {
            String url = "https://github.com/RiftWalker0/OneStop/releases/latest/download/app-debug.apk";
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            req.setTitle("One Stop update");
            req.setDestinationInExternalFilesDir(this, "Downloads", "OneStop-latest.apk");
            downloadId = dm.enqueue(req);
        });
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        try { unregisterReceiver(receiver); } catch (Throwable ignored){}
    }
    private boolean onNav(MenuItem item){
        drawer.close();
        int id = item.getItemId();
        if (id==R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
        else if (id==R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
        else if (id==R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
        else if (id==R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
        return true;
    }
}
