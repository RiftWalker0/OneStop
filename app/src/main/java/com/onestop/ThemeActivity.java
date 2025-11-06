package com.onestop;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThemeActivity extends AppCompatActivity {

    private void applyTheme() {
        int mode = Prefs.getTheme(this);
        switch (mode) {
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        RadioGroup group = findViewById(R.id.theme_group);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbMaterial = findViewById(R.id.rb_material);
        RadioButton rbBlack = findViewById(R.id.rb_black);

        int mode = Prefs.getTheme(this);
        rbWhite.setChecked(mode==0);
        rbMaterial.setChecked(mode==1);
        rbBlack.setChecked(mode==2);

        group.setOnCheckedChangeListener((g, id) -> {
            int newMode = (id==R.id.rb_white)?0 : (id==R.id.rb_material?1:2);
            Prefs.setTheme(this, newMode);
            recreate();
        });
    }
}
