package com.onestop;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private androidx.drawerlayout.widget.DrawerLayout drawer;
    private TextView state;
    private ToggleView toggle;
    private int lastTheme;


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
        lastTheme = Prefs.getTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        state = findViewById(R.id.state);
        toggle = findViewById(R.id.toggleView);

        ImageView btnMenu = findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(v -> drawer.openDrawer(Gravity.START));

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawers();
            int id = item.getItemId();
            if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        boolean initialOn = readBothEnabled(getContentResolver());
        toggle.setChecked(initialOn);
        updateStateText(initialOn);
        toggle.setOnToggleChanged(this::onToggle);

            // If launched with request to open drawer from sub-pages
            if (getIntent() != null && getIntent().getBooleanExtra("open_drawer", false)) {
                drawer.post(() -> drawer.openDrawer(android.view.Gravity.START));
                getIntent().removeExtra("open_drawer");
            }
    }

    @Override protected void onResume() {
        super.onResume();
        if (lastTheme != Prefs.getTheme(this)) recreate();
    }

    private void onToggle(boolean on) {
        if (!hasWriteSecureSettings()) {
            Toast.makeText(this,
                "Grant WRITE_SECURE_SETTINGS via ADB:\nadb shell pm grant com.onestop android.permission.WRITE_SECURE_SETTINGS",
                Toast.LENGTH_LONG).show();
            toggle.setOnToggleChanged(null);
            toggle.setChecked(readBothEnabled(getContentResolver()));
            toggle.setOnToggleChanged(this::onToggle);

            // If launched with request to open drawer from sub-pages
            if (getIntent() != null && getIntent().getBooleanExtra("open_drawer", false)) {
                drawer.post(() -> drawer.openDrawer(android.view.Gravity.START));
                getIntent().removeExtra("open_drawer");
            }
            updateStateText(toggle.isChecked());
            return;
        }
        if (applyDebugSetting(on)) updateStateText(on);
        else {
            Toast.makeText(this, "Failed to write system settings.", Toast.LENGTH_SHORT).show();
            boolean actual = readBothEnabled(getContentResolver());
            toggle.setOnToggleChanged(null);
            toggle.setChecked(actual);
            toggle.setOnToggleChanged(this::onToggle);

            // If launched with request to open drawer from sub-pages
            if (getIntent() != null && getIntent().getBooleanExtra("open_drawer", false)) {
                drawer.post(() -> drawer.openDrawer(android.view.Gravity.START));
                getIntent().removeExtra("open_drawer");
            }
            updateStateText(actual);
        }
    }

    private void updateStateText(boolean on) { state.setText(on ? R.string.debug_on : R.string.debug_off); }

    private boolean hasWriteSecureSettings() {
        return checkSelfPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean readBothEnabled(ContentResolver resolver) {
        int dev = Settings.Global.getInt(resolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        int adb = Settings.Global.getInt(resolver, Settings.Global.ADB_ENABLED, 0);
        return dev == 1 && adb == 1;
    }

    private boolean setGlobalInt(ContentResolver resolver, String key, int value) {
        try { return Settings.Global.putInt(resolver, key, value); }
        catch (Throwable t) { return false; }
    }

    private boolean applyDebugSetting(boolean on) {
        boolean okDev = setGlobalInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, on ? 1 : 0);
        boolean okAdb = setGlobalInt(getContentResolver(), Settings.Global.ADB_ENABLED, on ? 1 : 0);
        return okDev && okAdb;
    }
}
