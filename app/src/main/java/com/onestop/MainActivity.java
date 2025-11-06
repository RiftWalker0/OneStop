package com.onestop;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawer;
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(this::onNav);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.home);
        tb.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        tb.setNavigationOnClickListener(v -> drawer.open());
        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.content_main, content, true);

        ToggleButton toggle = findViewById(R.id.toggle);
        TextView tv = findViewById(R.id.tv_state);
        boolean on = Prefs.getState(this);
        toggle.setChecked(on);
        tv.setText(on ? R.string.debug_on : R.string.debug_off);
        toggle.setOnCheckedChangeListener((b, isOn) -> {
            Prefs.setState(this, isOn);
            tv.setText(isOn ? R.string.debug_on : R.string.debug_off);
            try {
                Settings.Global.putInt(getContentResolver(), "development_settings_enabled", isOn ? 1 : 0);
                Settings.Global.putInt(getContentResolver(), "adb_enabled", isOn ? 1 : 0);
            } catch (Throwable ignored) {}
        });
    }
    private boolean onNav(MenuItem item){
        drawer.close();
        int id = item.getItemId();
        if (id==R.id.nav_home) return true;
        if (id==R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
        else if (id==R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
        else if (id==R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
        else if (id==R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
        return true;
    }
}
