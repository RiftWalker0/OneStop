
package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(getString(R.string.home));
        tb.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_menu_overflow_material);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        tb.setNavigationOnClickListener(v -> drawer.open());

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(item -> {
            drawer.close();
            int id = item.getItemId();
            if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        ToggleButton toggle = findViewById(R.id.toggle);
        TextView tv = findViewById(R.id.tv_state);

        boolean saved = Prefs.getState(this);
        toggle.setChecked(saved);
        tv.setText(saved ? R.string.toggle_on : R.string.toggle_off);

        toggle.setOnCheckedChangeListener((b, on) -> {
            Prefs.setState(this, on);
            tv.setText(on ? R.string.toggle_on : R.string.toggle_off);
            try {
                Settings.Global.putInt(getContentResolver(), "development_settings_enabled", on ? 1 : 0);
                Settings.Global.putInt(getContentResolver(), "adb_enabled", on ? 1 : 0);
            } catch (Exception ignored) {}
        });
    }
}
