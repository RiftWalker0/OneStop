package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseActivity extends AppCompatActivity {
    protected DrawerLayout drawer;

    protected void applyTheme() {
        int t = Prefs.getTheme(this);
        if (t == Prefs.THEME_WHITE) setTheme(R.style.Theme_OneStop_White);
        else if (t == Prefs.THEME_MATERIAL) setTheme(R.style.Theme_OneStop);
        else setTheme(R.style.Theme_OneStop_Black);
    }

    protected void setupChrome(String title) {
        drawer = findViewById(R.id.drawer_layout);
        TextView tv = findViewById(R.id.tv_title);
        if (tv != null) tv.setText(title);
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
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
