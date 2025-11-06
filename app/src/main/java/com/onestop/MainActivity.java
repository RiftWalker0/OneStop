
package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireHeader(R.string.home);

        Switch sw = findViewById(R.id.sw_toggle);
        TextView tv = findViewById(R.id.tv_state);
        if (sw != null && tv != null){
            sw.setOnCheckedChangeListener((b, checked) -> {
                try {
                    Settings.Global.putInt(getContentResolver(), "development_settings_enabled", checked ? 1 : 0);
                    Settings.Global.putInt(getContentResolver(), "adb_enabled", checked ? 1 : 0);
                } catch (Exception ignored){}
                tv.setText(checked ? R.string.debug_on : R.string.debug_off);
            });
        }

        findViewById(R.id.nav_setup).setOnClickListener(v -> startActivity(new Intent(this, SetupActivity.class)));
        findViewById(R.id.nav_theme).setOnClickListener(v -> startActivity(new Intent(this, ThemeActivity.class)));
        findViewById(R.id.nav_updates).setOnClickListener(v -> startActivity(new Intent(this, UpdatesActivity.class)));
        findViewById(R.id.nav_about).setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
    }
}
