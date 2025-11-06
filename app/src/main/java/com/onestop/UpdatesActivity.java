
package com.onestop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

public class UpdatesActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(getString(R.string.updates));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        tb.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_menu_overflow_material);
        tb.setNavigationOnClickListener(v -> drawer.open());

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(item -> {
            drawer.close();
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        Button btnGit = findViewById(R.id.btn_github);
        btnGit.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/RiftWalker0/OneStop/releases/latest");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });

        Button btnDl = findViewById(R.id.btn_download);
        btnDl.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/RiftWalker0/OneStop/releases/latest");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
    }
}
