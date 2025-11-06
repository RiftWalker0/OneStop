
package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.drawerlayout.widget.DrawerLayout;

public class SetupActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(getString(R.string.setup));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        tb.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_menu_overflow_material);
        tb.setNavigationOnClickListener(v -> drawer.open());

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(item -> {
            drawer.close();
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        TabLayout tabs = findViewById(R.id.tabs);
        TextView tvPC = findViewById(R.id.tv_pc);
        TextView tvMobile = findViewById(R.id.tv_mobile);
        TabLayout.Tab pc = tabs.newTab().setText("PC");
        TabLayout.Tab mobile = tabs.newTab().setText("Mobile");
        tabs.addTab(pc);
        tabs.addTab(mobile);
        tabs.selectTab(pc);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                boolean isPC = tab == pc;
                tvPC.setVisibility(isPC ? android.view.View.VISIBLE : android.view.View.GONE);
                tvMobile.setVisibility(isPC ? android.view.View.GONE : android.view.View.VISIBLE);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}
