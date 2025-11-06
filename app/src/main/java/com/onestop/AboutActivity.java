package com.onestop;

import android.os.Bundle;
import android.widget.TextView;
import android.content.pm.PackageManager;
import android.os.Build;
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
