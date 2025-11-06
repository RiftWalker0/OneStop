package com.onestop;

import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class OneStopApp extends Application {
    @Override public void onCreate() {
        super.onCreate();
        if (Prefs.getTheme(this) == 1) {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }
    }
}
