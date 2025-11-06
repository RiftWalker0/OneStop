package com.onestop;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {
    private ImageView inner;
    private TextView tv;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupChrome(getString(R.string.home));

        inner = findViewById(R.id.toggle_inner);
        tv = findViewById(R.id.tv_state);

        View holder = findViewById(R.id.toggle_holder);
        if (holder != null) {
            holder.setOnClickListener(v -> toggle());
        }
        refresh();
    }

    private void toggle() {
        boolean on = isOn();
        setSecure("development_settings_enabled", on ? 0 : 1);
        setSecure("adb_enabled", on ? 0 : 1);
        refresh();
    }

    private void refresh() {
        boolean on = isOn();
        inner.setImageResource(on ? R.drawable.toggle_inner_green : R.drawable.toggle_inner_red);
        tv.setText(on ? R.string.toggle_on : R.string.toggle_off);
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

    private void setSecure(String key, int value) {
        try {
            Settings.Global.putInt(getContentResolver(), key, value);
        } catch (Throwable ignored) {}
    }
}
