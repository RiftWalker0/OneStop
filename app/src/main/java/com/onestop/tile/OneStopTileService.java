package com.onestop.tile;

import android.graphics.drawable.Icon;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import com.onestop.R;

public class OneStopTileService extends TileService {
    @Override public void onStartListening() {
        updateTile();
    }

    @Override public void onClick() {
        boolean on = isOn();
        set("development_settings_enabled", on ? 0 : 1);
        set("adb_enabled", on ? 0 : 1);
        updateTile();
    }

    private void updateTile() {
        Tile t = getQsTile();
        if (t == null) return;
        boolean on = isOn();
        t.setState(on ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        t.setIcon(Icon.createWithResource(this, on ? R.drawable.ic_qs_on : R.drawable.ic_qs_off));
        t.setLabel("One Stop");
        t.updateTile();
    }

    private boolean isOn() {
        try {
            int a = Settings.Global.getInt(getContentResolver(), "adb_enabled", 0);
            int d = Settings.Global.getInt(getContentResolver(), "development_settings_enabled", 0);
            return a == 1 && d == 1;
        } catch (Throwable t) {
            return false;
        }
    }

    private void set(String key, int value) {
        try { Settings.Global.putInt(getContentResolver(), key, value); } catch (Throwable ignored) {}
    }
}
