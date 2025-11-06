package com.onestop;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThemeActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        RadioGroup rg = findViewById(R.id.rgTheme);
        RadioButton rbWhite = findViewById(R.id.rbWhite);
        RadioButton rbBlack = findViewById(R.id.rbBlack);
        RadioButton rbMy = findViewById(R.id.rbMaterialYou);

        String t = Prefs.getTheme(this);
        if ("white".equals(t)) rbWhite.setChecked(true);
        else if ("black".equals(t)) rbBlack.setChecked(true);
        else rbMy.setChecked(true);

        rg.setOnCheckedChangeListener((g, id) -> {
            if (id == R.id.rbWhite) Prefs.setTheme(this, "white");
            else if (id == R.id.rbBlack) Prefs.setTheme(this, "black");
            else Prefs.setTheme(this, "dynamic");
            recreate();
        });
    }
}
