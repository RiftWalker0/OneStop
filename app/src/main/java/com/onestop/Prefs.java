package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String P = "onestop_prefs";
    private static final String KEY_THEME = "theme"; // 0=M3,1=BLACK,2=WHITE

    public static final int THEME_M3 = 0;
    public static final int THEME_BLACK = 1;
    public static final int THEME_WHITE = 2;

    public static int getTheme(Context c) {
        return c.getSharedPreferences(P, Context.MODE_PRIVATE).getInt(KEY_THEME, THEME_M3);
    }
    public static void setTheme(Context c, int theme) {
        c.getSharedPreferences(P, Context.MODE_PRIVATE).edit().putInt(KEY_THEME, theme).apply();
    }
}
