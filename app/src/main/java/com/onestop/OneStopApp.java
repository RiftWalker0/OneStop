package com.onestop;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.onestop.R;
import com.onestop.BuildConfig;
import android.widget.RadioGroup;
import android.widget.RadioButton;
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


import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class OneStopApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (Prefs.getTheme(this) == 1) {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }
    }
}