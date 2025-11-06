
package com.onestop;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

public class AboutActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(getString(R.string.about));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        tb.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_menu_overflow_material);
        tb.setNavigationOnClickListener(v -> drawer.open());

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(item -> {
            drawer.close();
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            return true;
        });

        TextView tvVersion = findViewById(R.id.tv_version);
        try {
            PackageManager pm = getPackageManager();
            String ver = pm.getPackageInfo(getPackageName(), 0).versionName;
            long code = (Build.VERSION.SDK_INT >= 28) ? pm.getPackageInfo(getPackageName(), 0).getLongVersionCode() : pm.getPackageInfo(getPackageName(), 0).versionCode;
            tvVersion.setText(getString(R.string.version_label) + ": " + ver + " (" + code + ")");
        } catch (Exception e) {
            tvVersion.setText(getString(R.string.version_label) + ": ?");
        }
    }
}
