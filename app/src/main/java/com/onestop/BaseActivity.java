
package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {
    protected void applyTheme(){
        int t = Prefs.getTheme(this);
        if (t == Prefs.THEME_BLACK) setTheme(R.style.Theme_OneStop_Black);
        else if (t == Prefs.THEME_MATERIAL) setTheme(R.style.Theme_OneStop_MaterialYou);
        else setTheme(R.style.Theme_OneStop_White);
    }
    protected void wireHeader(int titleRes){
        TextView tv = findViewById(R.id.tv_title);
        if (tv != null) tv.setText(titleRes);
        ImageView btn = findViewById(R.id.btn_menu);
        if (btn != null){
            btn.setOnClickListener(v -> {
                DrawerLayout d = findViewById(R.id.drawer_layout);
                if (d != null) d.open(Gravity.START);
            });
        }
        ImageView btnHome = findViewById(R.id.btn_home);
        if (btnHome != null){
            btnHome.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        }
    }
}
