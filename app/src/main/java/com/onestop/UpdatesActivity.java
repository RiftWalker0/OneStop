package com.onestop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

public class UpdatesActivity extends BaseActivity {
    private static final String RELEASES = "https://github.com/RiftWalker0/OneStop/releases/latest";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        MaterialButton btnRel = findViewById(R.id.btnReleases);
        MaterialButton btnDl  = findViewById(R.id.btnDownload);

        btnRel.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(RELEASES))));
        btnDl.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(RELEASES))));
    }
}
