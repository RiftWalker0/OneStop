
package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String P = "onestop_prefs";
    private static final String K_THEME = "theme"; // 0 white, 1 material you, 2 black
    public static final int THEME_WHITE = 0;
    public static final int THEME_MATERIAL = 1;
    public static final int THEME_BLACK = 2;

    public static int getTheme(Context c){ return c.getSharedPreferences(P, Context.MODE_PRIVATE).getInt(K_THEME, THEME_WHITE); }
    public static void setTheme(Context c, int t){ c.getSharedPreferences(P, Context.MODE_PRIVATE).edit().putInt(K_THEME, t).apply(); }
}
