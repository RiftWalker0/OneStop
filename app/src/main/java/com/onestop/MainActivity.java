package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton btn = findViewById(R.id.btnToggle);
        TextView tv = findViewById(R.id.tvState);

        boolean on = Prefs.isDebug(this);
        updateUi(btn, tv, on);

        btn.setOnClickListener(v -> {
            boolean now = !Prefs.isDebug(this);
            Prefs.setDebug(this, now);
            // Attempt to write secure settings (requires granted permission)
            try {
                Settings.Global.putInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, now ? 1 : 0);
                Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, now ? 1 : 0);
            } catch (Throwable ignored){}
            updateUi(btn, tv, now);
            com.onestop.tile.OneStopTileService.requestTileUpdate(this);
        });

        findViewById(R.id.title).setOnClickListener(v -> startActivity(new Intent(this, ThemeActivity.class)));
    }

    private void updateUi(MaterialButton btn, TextView tv, boolean on){
        btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(getColor(on ? R.color.green_on : R.color.red_off)));
        tv.setText(on ? R.string.debug_on : R.string.debug_off);
    }
}
