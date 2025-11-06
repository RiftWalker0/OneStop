package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class SetupActivity extends BaseActivity {
    private DrawerLayout drawer;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> drawer.openDrawer(Gravity.START));
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) { /* stay */ }
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            drawer.closeDrawers();
            return true;
        });

        TabLayout tabs = findViewById(R.id.tabs);
        TextView tvPC = findViewById(R.id.tv_pc);
        TextView tvMobile = findViewById(R.id.tv_mobile);

        if (tabs != null) {
            TabLayout.Tab pc = tabs.newTab().setText("PC");
            TabLayout.Tab mobile = tabs.newTab().setText("Mobile");
            tabs.addTab(pc);
            tabs.addTab(mobile);
            tabs.selectTab(pc);
            if (tvPC != null) tvPC.setVisibility(android.view.View.VISIBLE);
            if (tvMobile != null) tvMobile.setVisibility(android.view.View.GONE);
            tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override public void onTabSelected(TabLayout.Tab tab) {
                    boolean isPC = tab == pc;
                    if (tvPC != null) tvPC.setVisibility(isPC ? android.view.View.VISIBLE : android.view.View.GONE);
                    if (tvMobile != null) tvMobile.setVisibility(isPC ? android.view.View.GONE : android.view.View.VISIBLE);
                }
                @Override public void onTabUnselected(TabLayout.Tab tab) { }
                @Override public void onTabReselected(TabLayout.Tab tab) { }
            });
        }
    }
}
