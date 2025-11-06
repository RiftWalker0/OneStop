package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ThemeActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    private void applyTheme() {
        int mode = Prefs.getTheme(this);
        switch (mode) {
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
            nav.setCheckedItem(R.id.nav_theme);
            nav.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (drawer != null) drawer.closeDrawers();
                if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
                else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
                else if (id == R.id.nav_theme) {/* current */}
                else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
                else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
                return true;
            });
        }

        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> { if (drawer != null) drawer.openDrawer(Gravity.START); });
        }

        // Theme choice radios (if present): rb_white, rb_material_you, rb_black
        int idWhite = getResources().getIdentifier("rb_white", "id", getPackageName());
int idMy = getResources().getIdentifier("rb_material_you", "id", getPackageName());
int idBlack = getResources().getIdentifier("rb_black", "id", getPackageName());
RadioButton rbWhite = (idWhite != 0) ? findViewById(idWhite) : null;
RadioButton rbMy = (idMy != 0) ? findViewById(idMy) : null;
RadioButton rbBlack = (idBlack != 0) ? findViewById(idBlack) : null;
        if (rbWhite != null && rbMy != null && rbBlack != null) {
            int mode = Prefs.getTheme(this);
            rbWhite.setChecked(mode == 0);
            rbMy.setChecked(mode == 1);
            rbBlack.setChecked(mode != 0 && mode != 1);

            android.view.View.OnClickListener l = v -> {
                int m = 2;
                if (v == rbWhite) m = 0;
                else if (v == rbMy) m = 1;
                else if (v == rbBlack) m = 2;
                Prefs.setTheme(this, m);
                recreate();
            };
            rbWhite.setOnClickListener(l);
            rbMy.setOnClickListener(l);
            rbBlack.setOnClickListener(l);
        }

        // QS icon style radios
        if (rg != null && rbMono != null && rbColor != null) {
            boolean mono = Prefs.getQsIconMonochrome(this);
            rbMono.setChecked(mono);
            rbColor.setChecked(!mono);
            rg.setOnCheckedChangeListener((g, id) -> {
                });
        }
    }
}
