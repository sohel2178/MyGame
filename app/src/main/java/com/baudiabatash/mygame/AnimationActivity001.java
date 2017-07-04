package com.baudiabatash.mygame;

import android.app.Activity;
import android.os.Bundle;

import com.baudiabatash.mygame.Layout.Animation_001_Layout;

public class AnimationActivity001 extends Activity {

    private Animation_001_Layout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_animation001);
        layout = new Animation_001_Layout(this);
        setContentView(layout);
    }
}
