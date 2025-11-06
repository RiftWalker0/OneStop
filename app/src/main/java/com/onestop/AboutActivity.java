
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
        wireHeader(R.string.about);

        TextView v = findViewById(R.id.tv_version);
        TextView b = findViewById(R.id.tv_build);
        try {
            String pkg = getPackageName();
            String ver;
            long code;
            if (Build.VERSION.SDK_INT >= 33) {
                var info = getPackageManager().getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0));
                ver = info.versionName;
                code = info.getLongVersionCode();
            } else {
                @SuppressWarnings("deprecation")
                var info = getPackageManager().getPackageInfo(pkg, 0);
                ver = info.versionName;
                @SuppressWarnings("deprecation")
                long c = info.versionCode;
                code = c;
            }
            if (v!=null) v.setText(getString(R.string.version_label)+": "+ver);
            if (b!=null) b.setText(getString(R.string.build_label)+": "+code);
        } catch (Exception ignored){}
    }
}
