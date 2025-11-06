package com.onestop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;

public class SetupActivity extends BaseActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        setupChrome(getString(R.string.setup));

        TabLayout tabs = findViewById(R.id.tabs);
        TextView tvPC = findViewById(R.id.tv_pc);
        TextView tvMobile = findViewById(R.id.tv_mobile);

        TabLayout.Tab pc = tabs.newTab().setText(getString(R.string.pc));
        TabLayout.Tab mobile = tabs.newTab().setText(getString(R.string.mobile));
        tabs.addTab(pc);
        tabs.addTab(mobile);
        tabs.selectTab(pc);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                boolean isPC = tab == pc;
                tvPC.setVisibility(isPC ? View.VISIBLE : View.GONE);
                tvMobile.setVisibility(isPC ? View.GONE : View.VISIBLE);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}
