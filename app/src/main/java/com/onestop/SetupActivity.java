package com.onestop;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class SetupActivity extends BaseActivity {
    private DrawerLayout drawer;
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(this::onNav);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.setup);
        tb.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        tb.setNavigationOnClickListener(v -> drawer.open());

        getLayoutInflater().inflate(R.layout.content_setup, findViewById(R.id.content_frame), true);
        TabLayout tabs = findViewById(R.id.tabs);
        TextView tvPC = findViewById(R.id.tv_pc);
        TextView tvMobile = findViewById(R.id.tv_mobile);
        TabLayout.Tab pc = tabs.newTab().setText(R.string.pc);
        TabLayout.Tab mobile = tabs.newTab().setText(R.string.mobile);
        tabs.addTab(pc); tabs.addTab(mobile); tabs.selectTab(pc);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override public void onTabSelected(TabLayout.Tab tab){
                boolean isPC = tab == pc;
                tvPC.setVisibility(isPC? android.view.View.VISIBLE: android.view.View.GONE);
                tvMobile.setVisibility(isPC? android.view.View.GONE: android.view.View.VISIBLE);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab){}
            @Override public void onTabReselected(TabLayout.Tab tab){}
        });
    }
    private boolean onNav(MenuItem item){
        drawer.close();
        int id = item.getItemId();
        if (id==R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
        else if (id==R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
        else if (id==R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
        else if (id==R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
        return true;
    }
}
