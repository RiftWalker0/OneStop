package com.onestop;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.color.DynamicColors;
public class BaseActivity extends AppCompatActivity {
    protected void applyTheme() {
        String t = Prefs.getTheme(this);
        if ("white".equals(t)) setTheme(R.style.Theme_OneStop_White);
        else if ("my".equals(t)) {
            setTheme(R.style.Theme_OneStop_White);
            DynamicColors.applyToActivityIfAvailable(this);
        } else setTheme(R.style.Theme_OneStop_Black);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }
}
