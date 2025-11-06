
package com.onestop;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;

public class UpdatesActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        wireHeader(R.string.updates);

        Button btn = findViewById(R.id.btn_download);
        if (btn != null){
            btn.setOnClickListener(v -> {
                Uri uri = Uri.parse("https://github.com/RiftWalker0/OneStop/releases/latest");
                DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                if (dm != null){
                    DownloadManager.Request r = new DownloadManager.Request(uri);
                    r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    dm.enqueue(r);
                }
            });
        }
    }
}
