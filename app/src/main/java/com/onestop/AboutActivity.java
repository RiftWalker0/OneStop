package com.onestop;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends BaseActivity {
    private DrawerLayout drawer;
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        if (nav != null) nav.setNavigationItemSelectedListener(this::onNav);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.about);
        tb.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        tb.setNavigationOnClickListener(v -> drawer.open());
        getLayoutInflater().inflate(R.layout.content_about, findViewById(R.id.content_frame), true);

        TextView tvV = findViewById(R.id.tv_version);
        TextView tvB = findViewById(R.id.tv_build);
        try {
            PackageManager pm = getPackageManager();
            String pkg = getPackageName();
            android.content.pm.PackageInfo info;
            if (Build.VERSION.SDK_INT >= 33) {
                info = pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0));
            } else {
                info = pm.getPackageInfo(pkg, 0);
            }
            String ver = info.versionName;
            long code = (Build.VERSION.SDK_INT >= 28) ? info.getLongVersionCode() : info.versionCode;
            tvV.setText(getString(R.string.version_label) + ": " + ver);
            tvB.setText(getString(R.string.build_label) + ": " + code);
        } catch (Exception e) {
            tvV.setText(getString(R.string.version_label) + ": ?");
            tvB.setText(getString(R.string.build_label) + ": ?");
        }
    }
    private boolean onNav(MenuItem item){
        drawer.close();
        int id = item.getItemId();
        if (id==R.id.nav_home) startActivity(new Intent(this, MainActivity.class));
        else if (id==R.id.nav_setup) startActivity(new Intent(this, SetupActivity.class));
        else if (id==R.id.nav_theme) startActivity(new Intent(this, ThemeActivity.class));
        else if (id==R.id.nav_updates) startActivity(new Intent(this, UpdatesActivity.class));
        return true;
    }
}
