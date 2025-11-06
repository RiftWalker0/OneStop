
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
        wireHeader(R.string.themes);

        RadioGroup rg = findViewById(R.id.rg_theme);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbMy = findViewById(R.id.rb_material_you);
        RadioButton rbBlack = findViewById(R.id.rb_black);

        int t = Prefs.getTheme(this);
        if (rbWhite!=null) rbWhite.setChecked(t==Prefs.THEME_WHITE);
        if (rbMy!=null) rbMy.setChecked(t==Prefs.THEME_MATERIAL);
        if (rbBlack!=null) rbBlack.setChecked(t==Prefs.THEME_BLACK);

        if (rg!=null){
            rg.setOnCheckedChangeListener((group, id)->{
                int sel = Prefs.THEME_WHITE;
                if (id == R.id.rb_material_you) sel = Prefs.THEME_MATERIAL;
                else if (id == R.id.rb_black) sel = Prefs.THEME_BLACK;
                Prefs.setTheme(this, sel);
                recreate();
            });
        }
    }
}
