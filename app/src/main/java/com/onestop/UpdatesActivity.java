package com.onestop;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import java.io.File;

public class UpdatesActivity extends BaseActivity {
    private long downloadId = -1;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id == downloadId) {
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                Cursor c = dm.query(new DownloadManager.Query().setFilterById(id));
                if (c != null && c.moveToFirst()) {
                    int status = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        String uriStr = c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
                        if (uriStr != null) {
                            try {
                                Uri fileUri = Uri.parse(uriStr);
                                File file = new File(fileUri.getPath());
                                Uri contentUri = FileProvider.getUriForFile(UpdatesActivity.this, getPackageName()+".provider", file);
                                Intent install = new Intent(Intent.ACTION_VIEW);
                                install.setDataAndType(contentUri, "application/vnd.android.package-archive");
                                install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                startActivity(install);
                            } catch (Exception e) {
                                Toast.makeText(UpdatesActivity.this, "Open from Downloads to install.", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(UpdatesActivity.this, "Download failed.", Toast.LENGTH_SHORT).show();
                    }
                    c.close();
                }
            }
        }
    };

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        setupChrome(getString(R.string.updates));

        Button open = findViewById(R.id.btn_open_github);
        Button dl = findViewById(R.id.btn_download_latest);
        open.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/RiftWalker0/OneStop/releases/latest");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
        dl.setOnClickListener(v -> startDirectDownload());

        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        try { unregisterReceiver(receiver); } catch (Throwable ignored) {}
    }

    private void startDirectDownload() {
        String url = "https://github.com/RiftWalker0/OneStop/releases/latest/download/OneStop.apk";
        try {
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));
            req.setTitle("One Stop");
            req.setDescription("Downloading update...");
            req.setDestinationInExternalPublicDir("Download", "OneStop-latest.apk");
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            downloadId = dm.enqueue(req);
            Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Unable to start download.", Toast.LENGTH_SHORT).show();
        }
    }
}
