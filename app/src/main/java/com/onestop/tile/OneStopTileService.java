package com.onestop.tile;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.provider.Settings;

import com.onestop.R;

public class OneStopTileService extends TileService {

    private boolean isOn() {
        try {
            int dev = Settings.Global.getInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
            int adb = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0);
            return dev == 1 && adb == 1;
        } catch (Exception e) { return false; }
    }

    private void setState(boolean on) {
        try {
            Settings.Global.putInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, on ? 1 : 0);
            Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, on ? 1 : 0);
        } catch (Exception ignored) {}
    }

    private void refresh() {
        Tile t = getQsTile();
        if (t != null) {
            boolean on = isOn();
            t.setState(on ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
            t.setIcon(android.graphics.drawable.Icon.createWithResource(this, on ? R.drawable.ic_qs_toggle_on : R.drawable.ic_qs_toggle_off));
            t.updateTile();
        }
    }

    @Override public void onStartListening() { refresh(); }
    @Override public void onClick() {
        setState(!isOn());
        refresh();
    }
}
