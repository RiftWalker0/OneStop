package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String P = "one_stop_prefs";

    public static void setTheme(Context ctx, String theme) {
        ctx.getSharedPreferences(P, Context.MODE_PRIVATE).edit().putString("theme", theme).apply();
    }
    public static String getTheme(Context ctx) {
        return ctx.getSharedPreferences(P, Context.MODE_PRIVATE).getString("theme", "black");
    }
}
