package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ThemeActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> { if (drawer != null) drawer.openDrawer(Gravity.START); });

        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            drawer.closeDrawers();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        RadioGroup group = findViewById(R.id.theme_group);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbMy = findViewById(R.id.rb_material_you);
        RadioButton rbBlack = findViewById(R.id.rb_black);

        int t = Prefs.getTheme(this);
        rbWhite.setChecked(t == Prefs.THEME_WHITE);
        rbMy.setChecked(t == Prefs.THEME_M3);
        rbBlack.setChecked(t == Prefs.THEME_BLACK);

        group.setOnCheckedChangeListener((g, id) -> {
            if (id == R.id.rb_white) Prefs.setTheme(this, Prefs.THEME_WHITE);
            else if (id == R.id.rb_material_you) Prefs.setTheme(this, Prefs.THEME_M3);
            else if (id == R.id.rb_black) Prefs.setTheme(this, Prefs.THEME_BLACK);
            recreate();
        });
    }
}
