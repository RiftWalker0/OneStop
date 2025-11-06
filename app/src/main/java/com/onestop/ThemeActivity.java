package com.onestop;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.view.Gravity;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.ImageView;
import android.content.Intent;

public class ThemeActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    private void applyTheme() {
    private DrawerLayout drawer;
        int mode = Prefs.getTheme(this);
        switch (mode) {
    private DrawerLayout drawer;
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    private DrawerLayout drawer;
        applyTheme();
        super.onCreate(savedInstanceState);

                        setContentView(R.layout.activity_theme);
                        RadioGroup rg = findViewById(R.id.rg_qs_style);
                        RadioButton rbMono = findViewById(R.id.rb_qs_mono);
                        RadioButton rbColor = findViewById(R.id.rb_qs_color);
                        if (rg != null && rbMono != null && rbColor != null) {
                            boolean mono = Prefs.getQsIconMonochrome(this);
                            rbMono.setChecked(mono);
                            rbColor.setChecked(!mono);
                            rg.setOnCheckedChangeListener((g, id) -> {
                                boolean m = (id == R.id.rb_qs_mono);
                                Prefs.setQsIconMonochrome(this, m);
                            });
                        }
                        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
            nav.setCheckedItem(R.id.nav_theme);
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
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> { if (drawer != null) drawer.openDrawer(Gravity.START); });
        ImageView btnMenu = findViewById(com.onestop.R.id.btn_menu);
if (btnMenu != null) { btnMenu.setOnClickListener(v -> {
    private DrawerLayout drawer;
    Intent i = new Intent(this, MainActivity.class);
    i.putExtra("open_drawer", true);
    startActivity(i);
});}
RadioGroup group = findViewById(R.id.theme_group);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbMaterial = findViewById(R.id.rb_material);
        RadioButton rbBlack = findViewById(R.id.rb_black);

        int mode = Prefs.getTheme(this);
        rbWhite.setChecked(mode==0);
        rbMaterial.setChecked(mode==1);
        rbBlack.setChecked(mode==2);

        group.setOnCheckedChangeListener((g, id) -> {
    private DrawerLayout drawer;
            int newMode = (id==R.id.rb_white)?0 : (id==R.id.rb_material?1:2);
            Prefs.setTheme(this, newMode);
            recreate();
        });
    }
}
