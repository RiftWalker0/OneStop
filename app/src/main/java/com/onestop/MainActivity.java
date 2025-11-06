package com.onestop;

import android.content.Intent;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.widget.ToggleButton;

public class MainActivity extends BaseActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> { if (drawer != null) drawer.openDrawer(Gravity.START); });

        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
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

        ToggleButton toggle = findViewById(R.id.toggle_debug);
        TextView state = findViewById(R.id.tv_state);

        boolean on = isDebugOn();
        toggle.setChecked(on);
        updateStateText(state, on);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, isChecked ? 1 : 0);
                    Settings.Global.putInt(getContentResolver(), "development_settings_enabled", isChecked ? 1 : 0);
                    updateStateText(state, isChecked);
                } catch (SecurityException se) {
                    Toast.makeText(MainActivity.this, "Grant WRITE_SECURE_SETTINGS first.", Toast.LENGTH_LONG).show();
                    buttonView.setChecked(!isChecked);
                }
            }
        });
    }

    private void updateStateText(TextView tv, boolean on) {
        if (tv != null) tv.setText(on ? getString(R.string.debug_on) : getString(R.string.debug_off));
    }

    private boolean isDebugOn() {
        try {
            int adb = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0);
            int dev = Settings.Global.getInt(getContentResolver(), "development_settings_enabled", 0);
            return adb == 1 && dev == 1;
        } catch (Exception e) { return false; }
    }
}
