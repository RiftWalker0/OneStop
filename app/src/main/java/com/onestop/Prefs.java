package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String P = "prefs";
    public static final int THEME_WHITE = 0;
    public static final int THEME_BLACK = 1;
    public static final int THEME_MATERIAL = 2;

    public static int getTheme(Context c) {
        return c.getSharedPreferences(P, Context.MODE_PRIVATE).getInt("theme", THEME_BLACK);
    }
    public static void setTheme(Context c, int t) {
        c.getSharedPreferences(P, Context.MODE_PRIVATE).edit().putInt("theme", t).apply();
    }

    public static boolean getQsMono(Context c) {
        return c.getSharedPreferences(P, Context.MODE_PRIVATE).getBoolean("qs_mono", true);
    }
    public static void setQsMono(Context c, boolean m) {
        c.getSharedPreferences(P, Context.MODE_PRIVATE).edit().putBoolean("qs_mono", m).apply();
    }
}
