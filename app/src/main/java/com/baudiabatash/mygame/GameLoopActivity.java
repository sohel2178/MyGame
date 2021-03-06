package com.baudiabatash.mygame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.baudiabatash.mygame.Layout.GameLoopLayout;

public class GameLoopActivity extends Activity {

    private GameLoopLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout = new GameLoopLayout(this);
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
