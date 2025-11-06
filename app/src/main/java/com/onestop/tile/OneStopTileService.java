package com.onestop.tile;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.onestop.R;

import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.widget.Button;
import androidx.core.content.FileProvider;


import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class OneStopTileService extends TileService {
    @Override public void onStartListening() {
        super.onStartListening();
        updateTile();
    }
    @Override public void onClick() {
        super.onClick();
        boolean on = readBothEnabled();
        boolean target = !on;
        boolean okDev = setGlobalInt(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, target ? 1 : 0);
        boolean okAdb = setGlobalInt(Settings.Global.ADB_ENABLED, target ? 1 : 0);
        updateTile();
    }
    private void updateTile() {
        Tile t = getQsTile();
        if (t == null) return;
        boolean on = readBothEnabled();
        t.setState(on ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        t.updateTile();
    }
    private boolean readBothEnabled() {
        int dev = Settings.Global.getInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        int adb = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0);
        return dev == 1 && adb == 1;
    }
    private boolean setGlobalInt(String key, int value) {
        try { return Settings.Global.putInt(getContentResolver(), key, value); }
        catch (Throwable t) { return false; }
    }
}