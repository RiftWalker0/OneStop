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
    private DrawerLayout drawer;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> drawer.openDrawer(Gravity.START));
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) { /* stay */ }
            drawer.closeDrawers();
            return true;
        });

        TextView tv = findViewById(R.id.tv_version);
        try {
            String ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            long code;
            if (Build.VERSION.SDK_INT >= 28) code = getPackageManager().getPackageInfo(getPackageName(), 0).getLongVersionCode();
            else code = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            tv.setText(getString(R.string.version_label) + ": " + ver + " (" + code + ")\n" + getString(R.string.developer_note));
        } catch (Exception e) {
            tv.setText(getString(R.string.version_label) + ": ?");
        }
    }
}
