package com.onestop.tile;

import android.graphics.drawable.Icon;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import com.onestop.R;

public class OneStopTileService extends TileService {

    @Override public void onStartListening() {
        super.onStartListening();
        updateTile();
    }

    @Override public void onClick() {
        super.onClick();
        boolean on = isDebugOn();
        boolean target = !on;
        try {
            Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, target ? 1 : 0);
            Settings.Global.putInt(getContentResolver(), "development_settings_enabled", target ? 1 : 0);
        } catch (SecurityException ignored) {}
        updateTile();
    }

    private void updateTile() {
        Tile t = getQsTile();
        if (t == null) return;
        boolean on = isDebugOn();
        t.setState(on ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        t.setIcon(Icon.createWithResource(this, on ? R.drawable.ic_qs_on : R.drawable.ic_qs_off));
        t.setLabel(on ? getString(R.string.debug_on) : getString(R.string.debug_off));
        t.updateTile();
    }

    private boolean isDebugOn() {
        try {
            int adb = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0);
            int dev = Settings.Global.getInt(getContentResolver(), "development_settings_enabled", 0);
            return adb == 1 && dev == 1;
        } catch (Exception e) { return false; }
    }
}
