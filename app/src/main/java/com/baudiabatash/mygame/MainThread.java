package com.baudiabatash.mygame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Genius 03 on 7/4/2017.
 */

public class MainThread extends Thread {
    private int fps=30;
    private double avgFps;
    private SurfaceHolder holder;
    private GamePanel gamePanel;
    private boolean running;
    private static Canvas canvas;


    public MainThread(SurfaceHolder holder,GamePanel gamePanel){
        super();

        this.holder = holder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMilis;
        long waitTime;
        long totalTime=0;
        int frameCount=0;
        long targetTime=1000/fps;
        super.run();
    }
}
