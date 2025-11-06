package com.onestop;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.Nullable;

public class ThemeActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        setupChrome(getString(R.string.themes));

        RadioGroup group = findViewById(R.id.rg_theme);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbBlack = findViewById(R.id.rb_black);
        RadioButton rbMaterial = findViewById(R.id.rb_material);

        int t = Prefs.getTheme(this);
        rbWhite.setChecked(t == Prefs.THEME_WHITE);
        rbBlack.setChecked(t == Prefs.THEME_BLACK);
        rbMaterial.setChecked(t == Prefs.THEME_MATERIAL);

        group.setOnCheckedChangeListener((g, id) -> {
            int nt = Prefs.THEME_BLACK;
            if (id == R.id.rb_white) nt = Prefs.THEME_WHITE;
            else if (id == R.id.rb_black) nt = Prefs.THEME_BLACK;
            else if (id == R.id.rb_material) nt = Prefs.THEME_MATERIAL;
            Prefs.setTheme(this, nt);
            recreate();
        });
    }
}
