package com.onestop.tile;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import com.onestop.Prefs;
import com.onestop.R;

public class OneStopTileService extends TileService {

    @Override public void onStartListening() {
        super.onStartListening();
        refresh();
    }

    @Override public void onClick() {
        boolean now = !Prefs.isDebug(this);
        Prefs.setDebug(this, now);
        refresh();
    }

    private void refresh(){
        Tile t = getQsTile();
        if (t == null) return;
        boolean on = Prefs.isDebug(this);
        t.setState(on ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        t.setIcon(Icon.createWithResource(this, on ? R.drawable.ic_qs_on : R.drawable.ic_qs_off));
        t.setLabel(getString(on ? R.string.debug_on : R.string.debug_off));
        t.updateTile();
    }

    public static void requestTileUpdate(Context c){
        // No-op helper for future broadcasts; left simple.
    }
}
