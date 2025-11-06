package com.onestop;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.view.Gravity;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.ImageView;
import android.content.Intent;

public class AboutActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private void applyTheme() {
    private DrawerLayout drawer;
        int mode = Prefs.getTheme(this);
        switch (mode) {
    private DrawerLayout drawer;
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    private DrawerLayout drawer;
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
            nav.setCheckedItem(R.id.nav_about);
            nav.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                drawer.closeDrawers();
                if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
                else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
                else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
                else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
                else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
                return true;
            });
        }
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> { if (drawer != null) drawer.openDrawer(Gravity.START); });
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) {
    private DrawerLayout drawer;
            btnMenu.setOnClickListener(v -> {
    private DrawerLayout drawer;
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("open_drawer", true);
                startActivity(i);
            });
        }
        TextView tvVersion = findViewById(R.id.tv_version);
        try {
    private DrawerLayout drawer;
            PackageManager pm = getPackageManager();
            String pkg = getPackageName();
            android.content.pm.PackageInfo info;
            if (Build.VERSION.SDK_INT >= 33) {
    private DrawerLayout drawer;
                info = pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0));
            } else {
    private DrawerLayout drawer;
                info = pm.getPackageInfo(pkg, 0);
            }
            String ver = info.versionName;
            long code = (Build.VERSION.SDK_INT >= 28) ? info.getLongVersionCode() : info.versionCode;
            tvVersion.setText(getString(R.string.version_label) + ": " + ver + " (" + code + ")");
        } catch (Exception e) {
    private DrawerLayout drawer;
            tvVersion.setText(getString(R.string.version_label) + ": ?");
        }
    }
}
