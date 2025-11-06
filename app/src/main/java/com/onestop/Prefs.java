package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String NAME = "onestop_prefs";
    private static final String KEY_THEME_MODE = "theme_mode";
    private static final String KEY_LAST_THEME = "last_theme";

    // 0=White, 1=MaterialYou, 2=Black
    public static int getTheme(Context c) {
        return c.getSharedPreferences(NAME, Context.MODE_PRIVATE).getInt(KEY_THEME_MODE, 2);
    }
    public static void setTheme(Context c, int mode) {
        c.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putInt(KEY_THEME_MODE, mode).apply();
    }

    public static int getLastTheme(Context c) {
        return c.getSharedPreferences(NAME, Context.MODE_PRIVATE).getInt(KEY_LAST_THEME, -1);
    }
    public static void setLastTheme(Context c, int mode) {
        c.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putInt(KEY_LAST_THEME, mode).apply();
    }
}
