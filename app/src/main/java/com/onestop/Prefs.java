package com.onestop;
import android.content.Context;
import android.content.SharedPreferences;
public class Prefs {
    private static final String P = "onestop_prefs";
    private static final String K_THEME = "theme"; // black, white, my
    private static final String K_STATE = "state"; // toggle state
    public static void setTheme(Context c, String t){ c.getSharedPreferences(P,0).edit().putString(K_THEME,t).apply(); }
    public static String getTheme(Context c){ return c.getSharedPreferences(P,0).getString(K_THEME,"black"); }
    public static void setState(Context c, boolean on){ c.getSharedPreferences(P,0).edit().putBoolean(K_STATE,on).apply(); }
    public static boolean getState(Context c){ return c.getSharedPreferences(P,0).getBoolean(K_STATE,false); }
}
