package com.onestop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected void applyTheme() {
        String t = Prefs.getTheme(this);
        if ("white".equals(t)) setTheme(R.style.Theme_OneStop_White);
        else if ("black".equals(t)) setTheme(R.style.Theme_OneStop_Black);
        else setTheme(R.style.Theme_OneStop_Dynamic);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
    }
}
