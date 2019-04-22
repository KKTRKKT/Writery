package com.example.writery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class WriteSetting extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_setting_layout);
    }

    public void gotoWrite(View view) {
        onBackPressed();
    }

    public void checkbtn(View view) {
        onBackPressed();
    }
}
