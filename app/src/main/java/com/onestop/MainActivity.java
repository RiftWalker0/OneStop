package com.onestop;

import android.content.Intent;
import android.provider.Settings;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity {

    private DrawerLayout drawer;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        TextView title = findViewById(R.id.tv_title);
        title.setText(R.string.home);

        if (btnMenu != null) btnMenu.setOnClickListener(v -> drawer.openDrawer(Gravity.START));

        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
            nav.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) { /* stay */ }
                else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
                else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
                else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
                else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
                drawer.closeDrawers();
                return true;
            });
        }

        Switch sw = findViewById(R.id.switch_toggle);
        TextView state = findViewById(R.id.tv_state);

        boolean enabled = isDebugOn();
        sw.setChecked(enabled);
        updateStateText(state, enabled);

        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setDebug(isChecked);
            updateStateText(state, isChecked);
        });
    }

    private void updateStateText(TextView tv, boolean on) {
        if (tv != null) tv.setText(on ? R.string.debug_on : R.string.debug_off);
    }

    private boolean isDebugOn() {
        try {
            int dev = Settings.Global.getInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
            int adb = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0);
            return dev == 1 && adb == 1;
        } catch (Exception e) { return false; }
    }

    private void setDebug(boolean on) {
        try {
            Settings.Global.putInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, on ? 1 : 0);
            Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, on ? 1 : 0);
        } catch (Exception ignored) {}
    }
}
