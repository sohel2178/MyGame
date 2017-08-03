package com.baudiabatash.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baudiabatash.mygame.Layout.FixedDeltaGameLoopLayout;

public class FixedDeltaGameLoop extends AppCompatActivity {
    private FixedDeltaGameLoopLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = new FixedDeltaGameLoopLayout(this);
        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.resume();
    }

    @Override
    protected void onPause() {
        layout.pause();
        super.onPause();
    }
}
