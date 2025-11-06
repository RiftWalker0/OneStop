package com.onestop;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class AboutActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupChrome(getString(R.string.about));

        TextView tvVersion = findViewById(R.id.tv_version);
        try {
            PackageManager pm = getPackageManager();
            String pkg = getPackageName();
            android.content.pm.PackageInfo info;
            if (Build.VERSION.SDK_INT >= 33) {
                info = pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0));
            } else {
                info = pm.getPackageInfo(pkg, 0);
            }
            String ver = info.versionName;
            long code = (Build.VERSION.SDK_INT >= 28) ? info.getLongVersionCode() : info.versionCode;
            tvVersion.setText(getString(R.string.version_label) + ": " + ver + " (" + code + ")");
        } catch (Exception e) {
            tvVersion.setText(getString(R.string.version_label) + ": ?");
        }
    }
}
