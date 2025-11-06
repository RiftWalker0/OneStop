
package com.onestop;

import android.os.Bundle;
import androidx.annotation.Nullable;

public class SetupActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        wireHeader(R.string.setup);
    }
}
