
package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

public class ThemeActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(getString(R.string.themes));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        tb.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_menu_overflow_material);
        tb.setNavigationOnClickListener(v -> drawer.open());

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(item -> {
            drawer.close();
            int id = item.getItemId();
            if (id == R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
            else if (id == R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
            else if (id == R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
            else if (id == R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        RadioGroup rg = findViewById(R.id.rg_theme);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbBlack = findViewById(R.id.rb_black);
        RadioButton rbMy = findViewById(R.id.rb_my);

        String t = Prefs.getTheme(this);
        rbWhite.setChecked("white".equals(t));
        rbBlack.setChecked("black".equals(t));
        rbMy.setChecked("my".equals(t));

        rg.setOnCheckedChangeListener((g,id)->{
            String nt = "black";
            if (id == R.id.rb_white) nt = "white";
            else if (id == R.id.rb_my) nt = "my";
            Prefs.setTheme(this, nt);
            recreate();
        });
    }
}
