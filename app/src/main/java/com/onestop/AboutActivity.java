package com.onestop;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    private void applyTheme() {
        int mode = Prefs.getTheme(this);
        switch (mode) {
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
            nav.setCheckedItem(R.id.nav_about);
            nav.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (drawer != null) drawer.closeDrawers();
                if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
                else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
                else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
                else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
                else if (id == R.id.nav_about) {/* current */}
                return true;
            });
        }

        MaterialToolbar topBar = findViewById(R.id.top_bar);
        if (topBar != null) {
            topBar.setTitle(R.string.title_about);
            topBar.setNavigationOnClickListener(v -> {
                if (drawer != null) {
                    drawer.openDrawer(Gravity.START);
                }
            });
        }

        TextView tvVersion = findViewById(R.id.tv_version);
        if (tvVersion != null) {
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
}
