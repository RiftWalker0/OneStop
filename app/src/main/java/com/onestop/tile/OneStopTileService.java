package com.onestop.tile;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.provider.Settings;
import com.onestop.Prefs;
public class OneStopTileService extends TileService {
    @Override public void onStartListening() {
        super.onStartListening();
        boolean on = Prefs.getState(this);
        updateTile(on);
    }
    @Override public void onClick() {
        boolean on = !Prefs.getState(this);
        Prefs.setState(this, on);
        try {
            Settings.Global.putInt(getContentResolver(), "development_settings_enabled", on?1:0);
            Settings.Global.putInt(getContentResolver(), "adb_enabled", on?1:0);
        } catch (Throwable ignored) {}
        updateTile(on);
    }
    private void updateTile(boolean on){
        Tile t = getQsTile();
        if (t == null) return;
        t.setState(on ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        t.updateTile();
    }
}
