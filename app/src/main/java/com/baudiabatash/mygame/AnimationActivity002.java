package com.baudiabatash.mygame;

import android.app.Activity;
import android.os.Bundle;

import com.baudiabatash.mygame.Layout.Animation_002_Layout;

public class AnimationActivity002 extends Activity {
    private Animation_002_Layout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_animation002);

        layout = new Animation_002_Layout(this);
        setContentView(layout);
    }

    @Override
    protected void onPause() {
        layout.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        layout.resume();
        super.onResume();
    }
}
