package com.onestop;

import android.app.Application;
import android.os.Build;
import com.google.android.material.color.DynamicColors;

public class OneStopApp extends Application {
    @Override public void onCreate() {
        super.onCreate();
        if ("material".equals(Prefs.getTheme(this)) && Build.VERSION.SDK_INT >= 31) {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }
    }
}
