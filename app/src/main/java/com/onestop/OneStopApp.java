package com.onestop;

import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class OneStopApp extends Application {
    @Override public void onCreate() {
        super.onCreate();
        // Allow dynamic colors app-wide; theme switcher can use explicit themes as needed.
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
