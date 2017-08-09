package com.baudiabatash.mygame;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baudiabatash.mygame.Layout.TestLayout;

public class TestActivity extends AppCompatActivity {
    TestLayout testLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        testLayout = new TestLayout(this);

        setContentView(testLayout);
    }


    @Override
    protected void onResume() {
        super.onResume();

        testLayout.resume();

    }

    @Override
    protected void onPause() {
        testLayout.pause();
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
