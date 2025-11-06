
package com.onestop.tile;

import android.service.quicksettings.TileService;
import android.service.quicksettings.Tile;
import android.graphics.drawable.Icon;
import android.content.Intent;
import com.onestop.R;
import com.onestop.Prefs;

public class OneStopTileService extends TileService {
    @Override public void onStartListening() {
        super.onStartListening();
        updateTile();
    }
    @Override public void onClick() {
        int t = Prefs.THEME_WHITE; // no-op here; real app would toggle ADB
        updateTile();
    }
    private void updateTile(){
        Tile tile = getQsTile();
        if (tile == null) return;
        tile.setState(Tile.STATE_ACTIVE);
        tile.setIcon(Icon.createWithResource(this, R.drawable.ic_menu_24));
        tile.updateTile();
    }
}
