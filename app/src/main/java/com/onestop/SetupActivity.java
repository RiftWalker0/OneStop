package com.onestop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.content.Intent;
import android.view.Gravity;
import android.widget.ImageView;

public class SetupActivity extends AppCompatActivity {

    private void applyTheme() {
        int mode = Prefs.getTheme(this);
        switch (mode) {
            case 0: setTheme(R.style.Theme_OneStop_White); break;
            case 1: setTheme(R.style.Theme_OneStop_MaterialYou); break;
            default: setTheme(R.style.Theme_OneStop_Black); break;
        }
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);

                     setContentView(R.layout.activity_setup);

                     ImageView btnMenu = findViewById(R.id.btn_menu);
                     if (btnMenu != null) {
                         btnMenu.setOnClickListener(v -> {
                             Intent i = new Intent(this, MainActivity.class);
                             i.putExtra("open_drawer", true);
                             startActivity(i);
                         });
                     }

                     TabLayout tabs = findViewById(R.id.tab_layout);
                     TextView tvPC = findViewById(R.id.tv_pc);
                     TextView tvMobile = findViewById(R.id.tv_mobile);

                     TabLayout.Tab pc = tabs.newTab().setText("PC");
                     TabLayout.Tab mobile = tabs.newTab().setText("Mobile");
                     tabs.addTab(pc);
                     tabs.addTab(mobile);
                     tabs.selectTab(pc);

                     tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                         @Override public void onTabSelected(TabLayout.Tab tab) {
                             boolean isPC = tab == pc;
                             tvPC.setVisibility(isPC ? android.view.View.VISIBLE : android.view.View.GONE);
                             tvMobile.setVisibility(isPC ? android.view.View.GONE : android.view.View.VISIBLE);
                         }
                         @Override public void onTabUnselected(TabLayout.Tab tab) { }
                         @Override public void onTabReselected(TabLayout.Tab tab) { }
                     });
                 }
             }
