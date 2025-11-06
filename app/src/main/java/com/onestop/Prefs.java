
package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String FILE = "onestop";
    private static final String KEY_THEME = "theme"; // white, black, my
    private static final String KEY_STATE = "state";

    public static void setTheme(Context c, String t){ prefs(c).edit().putString(KEY_THEME, t).apply(); }
    public static String getTheme(Context c){ return prefs(c).getString(KEY_THEME, "black"); }

    public static void setState(Context c, boolean on){ prefs(c).edit().putBoolean(KEY_STATE, on).apply(); }
    public static boolean getState(Context c){ return prefs(c).getBoolean(KEY_STATE, false); }

    private static SharedPreferences prefs(Context c){ return c.getSharedPreferences(FILE, Context.MODE_PRIVATE); }
}
