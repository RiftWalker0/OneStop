package com.onestop.tile;

import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.graphics.drawable.Icon;
import android.service.quicksettings.TileService;

public class OneStopTileService extends TileService {
    private static final boolean MONOCHROME_ICON = true; // set false for colored icons
    @Override public void onStartListening() { super.onStartListening(); updateTile(); }
    @Override public void onClick() {
        super.onClick();
        boolean on = readBothEnabled();
        boolean target = !on;
        Settings.Global.putInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, target?1:0);
        Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, target?1:0);
        updateTile();
    }
    private void updateTile() {
    Tile t = getQsTile();
    if (t == null) return;
    boolean on = readBothEnabled();
    t.setState(on ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
    t.setIcon(Icon.createWithResource(this,
        on ? (MONOCHROME_ICON ? com.onestop.R.drawable.ic_qs_on_mono : com.onestop.R.drawable.ic_qs_on)
           : (MONOCHROME_ICON ? com.onestop.R.drawable.ic_qs_off_mono : com.onestop.R.drawable.ic_qs_off))
    );
    t.updateTile();
}

    private boolean readBothEnabled() {
        int dev = Settings.Global.getInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        int adb = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0);
        return dev == 1 && adb == 1;
    }
}
