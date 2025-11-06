package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String P = "prefs";
    private static final String K_THEME = "theme"; // "white","black","dynamic"
    private static final String K_DEBUG = "debug";

    public static void setTheme(Context c, String v){ c.getSharedPreferences(P,0).edit().putString(K_THEME, v).apply(); }
    public static String getTheme(Context c){ return c.getSharedPreferences(P,0).getString(K_THEME, "dynamic"); }

    public static void setDebug(Context c, boolean on){ c.getSharedPreferences(P,0).edit().putBoolean(K_DEBUG,on).apply(); }
    public static boolean isDebug(Context c){ return c.getSharedPreferences(P,0).getBoolean(K_DEBUG,false); }
}
