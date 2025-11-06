package com.onestop;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.onestop.R;
import com.onestop.BuildConfig;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.widget.Button;
import androidx.core.content.FileProvider;


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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        RadioGroup group = findViewById(R.id.theme_group);
        RadioButton rbWhite = findViewById(R.id.rb_white);
        RadioButton rbMaterial = findViewById(R.id.rb_material);
        RadioButton rbBlack = findViewById(R.id.rb_black);

        int mode = Prefs.getTheme(this);
        if (mode == 0) rbWhite.setChecked(true);
        else if (mode == 1) rbMaterial.setChecked(true);
        else rbBlack.setChecked(true);

        group.setOnCheckedChangeListener((g, id) -> {
            int newMode = 2;
            if (id == R.id.rb_white) newMode = 0;
            else if (id == R.id.rb_material) newMode = 1;
            else if (id == R.id.rb_black) newMode = 2;
            Prefs.setTheme(this, newMode);
            recreate();
        });
    }
}