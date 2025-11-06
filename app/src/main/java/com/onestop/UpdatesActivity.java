package com.onestop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class UpdatesActivity extends BaseActivity {
    private DrawerLayout drawer;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        drawer = findViewById(R.id.drawer_layout);
        ImageView btnMenu = findViewById(R.id.btn_menu);
        if (btnMenu != null) btnMenu.setOnClickListener(v -> drawer.openDrawer(Gravity.START));
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_updates) { /* stay */ }
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            drawer.closeDrawers();
            return true;
        });

        Button btn = findViewById(R.id.btn_github);
        if (btn != null) btn.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/RiftWalker0/OneStop/releases/latest");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
    }
}
