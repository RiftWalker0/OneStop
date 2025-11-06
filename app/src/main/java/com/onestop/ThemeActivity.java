package com.onestop;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class ThemeActivity extends BaseActivity {
    private DrawerLayout drawer;
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(this::onNav);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.themes);
        tb.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        tb.setNavigationOnClickListener(v -> drawer.open());
        getLayoutInflater().inflate(R.layout.content_theme, findViewById(R.id.content_frame), true);

        RadioGroup rg = findViewById(R.id.rg_theme);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbBlack = findViewById(R.id.rb_black);
        RadioButton rbMy = findViewById(R.id.rb_material_you);
        String cur = Prefs.getTheme(this);
        if ("white".equals(cur)) rbWhite.setChecked(true);
        else if ("my".equals(cur)) rbMy.setChecked(true);
        else rbBlack.setChecked(true);
        rg.setOnCheckedChangeListener((g, id) -> {
            if (id == R.id.rb_white) Prefs.setTheme(this, "white");
            else if (id == R.id.rb_material_you) Prefs.setTheme(this, "my");
            else Prefs.setTheme(this, "black");
            recreate();
        });
    }
    private boolean onNav(MenuItem item){
        drawer.close();
        int id = item.getItemId();
        if (id==R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
        else if (id==R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
        else if (id==R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
        else if (id==R.id.nav_about) startActivity(new Intent(this, AboutActivity.class));
        return true;
    }
}
