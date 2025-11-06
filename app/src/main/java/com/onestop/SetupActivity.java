package com.onestop;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetupActivity extends AppCompatActivity {

    private void applyTheme() {
        int mode = Prefs.getTheme(this);
        switch (mode) {
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        TextView title = findViewById(R.id.title_setup);
        try {
            Typeface coolJazz = Typeface.createFromAsset(getAssets(), "fonts/cooljazz.ttf");
            title.setTypeface(coolJazz);
        } catch (Throwable ignored) { }
    }
}
