package com.onestop;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> { if (drawer != null) drawer.openDrawer(Gravity.START); });

        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            drawer.closeDrawers();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        TextView tvVersion = findViewById(R.id.tv_version);
        try {
            PackageManager pm = getPackageManager();
            String pkg = getPackageName();
            android.content.pm.PackageInfo info;
            if (Build.VERSION.SDK_INT >= 33)
                info = pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0));
            else
                info = pm.getPackageInfo(pkg, 0);
            long code = (Build.VERSION.SDK_INT >= 28) ? info.getLongVersionCode() : info.versionCode;
            tvVersion.setText(getString(R.string.version_fmt, info.versionName, code));
        } catch (Exception e) {
            tvVersion.setText("Version: ?");
        }
    }
}
