package com.onestop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected void applyTheme() {
        int t = Prefs.getTheme(this);
        if (t == Prefs.THEME_BLACK) setTheme(R.style.Theme_OneStop_Black);
        else if (t == Prefs.THEME_WHITE) setTheme(R.style.Theme_OneStop_White);
        else setTheme(R.style.Theme_OneStop_M3);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
    }
}
