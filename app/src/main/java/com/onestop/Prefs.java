package com.onestop;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String NAME = "onestop_prefs";
    private static final String KEY_THEME_MODE = "theme_mode";
    public static int getTheme(Context c) {
        return c.getSharedPreferences(NAME, Context.MODE_PRIVATE).getInt(KEY_THEME_MODE, 2);
    }
    public static void setTheme(Context c, int mode) {
        c.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putInt(KEY_THEME_MODE, mode).apply();
    }

                        public static boolean getQsIconMonochrome(android.content.Context c) {
                            android.content.SharedPreferences p = c.getSharedPreferences("prefs", android.content.Context.MODE_PRIVATE);
                            return p.getBoolean("qs_icon_mono", true);
                        }
                        public static void setQsIconMonochrome(android.content.Context c, boolean mono) {
                            android.content.SharedPreferences p = c.getSharedPreferences("prefs", android.content.Context.MODE_PRIVATE);
                            p.edit().putBoolean("qs_icon_mono", mono).apply();
                        }
                        }
