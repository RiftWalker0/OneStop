
package com.onestop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void applyTheme() {
        String t = Prefs.getTheme(this);
        if ("white".equals(t)) {
            setTheme(R.style.Theme_OneStop_White);
        } else if ("my".equals(t)) {
            setTheme(R.style.Theme_OneStop); // Material You via DynamicColors
        } else {
            setTheme(R.style.Theme_OneStop_Black);
        }
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
    }
}
