package com.onestop;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.color.DynamicColors;

public abstract class BaseActivity extends AppCompatActivity {

    protected void applyTheme() {
        String theme = Prefs.getTheme(this);
        if ("material".equals(theme)) {
            // Apply Material You dynamic colors if available
            if (Build.VERSION.SDK_INT >= 31) {
                DynamicColors.applyToActivityIfAvailable(this);
            }
            setTheme(R.style.Theme_OneStop_MaterialYou);
        } else if ("white".equals(theme)) {
            setTheme(R.style.Theme_OneStop_White);
        } else {
            setTheme(R.style.Theme_OneStop_Black);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
    }
}
