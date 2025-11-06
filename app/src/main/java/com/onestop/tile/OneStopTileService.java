package com.onestop.tile;

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
