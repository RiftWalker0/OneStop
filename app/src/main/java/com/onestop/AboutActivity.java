package com.onestop;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvVersion = findViewById(R.id.tvVersion);
        String ver = "";
        long code = 0;
        try {
            PackageManager pm = getPackageManager();
            String pkg = getPackageName();
            if (Build.VERSION.SDK_INT >= 33) {
                PackageInfo info = pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0));
                ver = info.versionName;
                code = info.getLongVersionCode();
            } else {
                PackageInfo info = pm.getPackageInfo(pkg, 0);
                ver = info.versionName;
                code = Build.VERSION.SDK_INT >= 28 ? info.getLongVersionCode() : info.versionCode;
            }
        } catch (Exception ignored) {}
        tvVersion.setText(getString(R.string.version_label)+": "+ver+" ("+code+")");
    }
}
