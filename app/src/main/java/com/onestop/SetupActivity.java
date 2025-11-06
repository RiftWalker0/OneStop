package com.onestop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.view.Gravity;
import android.widget.ImageView;
import android.content.Intent;
import com.google.android.material.tabs.TabLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.content.Intent;
import android.view.Gravity;
import android.widget.ImageView;

public class SetupActivity extends AppCompatActivity {
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

                     setContentView(R.layout.activity_setup);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) {
            nav.setCheckedItem(R.id.nav_setup);
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
        ImageView btnMenu = findViewById(R.id.btn_menu);
                     if (btnMenu != null) {
    private DrawerLayout drawer;
                         btnMenu.setOnClickListener(v -> {
    private DrawerLayout drawer;
                             Intent i = new Intent(this, MainActivity.class);
                             i.putExtra("open_drawer", true);
                             startActivity(i);
                         });
                     }

                     TabLayout tabs = findViewById(R.id.tab_layout);
                     TextView tvPC = findViewById(R.id.tv_pc);
                     TextView tvMobile = findViewById(R.id.tv_mobile);

                     TabLayout.Tab pc = tabs.newTab().setText("PC");
                     TabLayout.Tab mobile = tabs.newTab().setText("Mobile");
                     tabs.addTab(pc);
                     tabs.addTab(mobile);
                     tabs.selectTab(pc);

                     tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    private DrawerLayout drawer;
                         @Override public void onTabSelected(TabLayout.Tab tab) {
    private DrawerLayout drawer;
                             boolean isPC = tab == pc;
                             tvPC.setVisibility(isPC ? android.view.View.VISIBLE : android.view.View.GONE);
                             tvMobile.setVisibility(isPC ? android.view.View.GONE : android.view.View.VISIBLE);
                         }
                         @Override public void onTabUnselected(TabLayout.Tab tab) { }
                         @Override public void onTabReselected(TabLayout.Tab tab) { }
                     });
                 }
             }
