package com.onestop;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private void applyTheme() {
        int mode = Prefs.getTheme(this);
        switch (mode) {
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvVersion = findViewById(R.id.tv_version);
        try {
            String ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            try {
    long code;
    if (android.os.Build.VERSION.SDK_INT >= 28) {
        code = getPackageManager().getPackageInfo(getPackageName(), 0).getLongVersionCode();
    } else {
        code = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
    }
    tvVersion.setText(getString(R.string.version_label) + ": " + ver + " (" + code + ")");
} catch (Exception e2) {
    tvVersion.setText(getString(R.string.version_label) + ": " + ver);
}

        } catch (Exception e) {
            tvVersion.setText(getString(R.string.version_label) + ": ?");
        }
    }
}
