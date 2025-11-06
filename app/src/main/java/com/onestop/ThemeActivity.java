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

    private DrawerLayout drawer;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> drawer.openDrawer(Gravity.START));
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) { /* stay */ }
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            drawer.closeDrawers();
            return true;
        });

        RadioGroup group = findViewById(R.id.rg_theme);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbMaterial = findViewById(R.id.rb_material);
        RadioButton rbBlack = findViewById(R.id.rb_black);

        String cur = Prefs.getTheme(this);
        if (rbWhite != null) rbWhite.setChecked("white".equals(cur));
        if (rbMaterial != null) rbMaterial.setChecked("material".equals(cur));
        if (rbBlack != null) rbBlack.setChecked("black".equals(cur));

        if (group != null) {
            group.setOnCheckedChangeListener((g, id) -> {
                String t = "black";
                if (id == R.id.rb_white) t = "white";
                else if (id == R.id.rb_material) t = "material";
                else if (id == R.id.rb_black) t = "black";
                Prefs.setTheme(this, t);
                recreate();
            });
        }
    }
}
