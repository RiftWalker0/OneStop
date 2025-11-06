package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
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
                else if (id == R.id.nav_theme) { /* current */ }
                else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
                else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
                return true;
            });
        }

        MaterialToolbar topBar = findViewById(R.id.top_bar);
        if (topBar != null) {
            topBar.setTitle(R.string.title_theme);
            topBar.setNavigationOnClickListener(v -> {
                if (drawer != null) {
                    drawer.openDrawer(Gravity.START);
                }
            });
        }

        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbMy    = findViewById(R.id.rb_material_you);
        RadioButton rbBlack = findViewById(R.id.rb_black);

        int mode = Prefs.getTheme(this);
        if (rbWhite != null) rbWhite.setChecked(mode == 0);
        if (rbMy != null)    rbMy.setChecked(mode == 1);
        if (rbBlack != null) rbBlack.setChecked(mode == 2);

        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {
            if (!isChecked) return;
            int newMode = mode;
            if (buttonView == rbWhite) newMode = 0;
            else if (buttonView == rbMy) newMode = 1;
            else if (buttonView == rbBlack) newMode = 2;
            if (newMode != mode) {
                Prefs.setTheme(this, newMode);
                recreate();
            }
        };

        if (rbWhite != null) rbWhite.setOnCheckedChangeListener(listener);
        if (rbMy != null)    rbMy.setOnCheckedChangeListener(listener);
        if (rbBlack != null) rbBlack.setOnCheckedChangeListener(listener);
    }
}
