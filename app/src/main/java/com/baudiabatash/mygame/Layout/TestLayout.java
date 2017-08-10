package com.baudiabatash.mygame.Layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.baudiabatash.mygame.Model.Rod;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sohel on 8/6/2017.
 */

public class TestLayout extends SurfaceView implements Runnable {
    private Thread thread = null;
    private boolean canDraw=false;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Context context;

    private static final String LOG="SOHEL";
    private boolean moveEventCalled;



    //Game Loop Variable
    private double frame_per_second;
    private double tLF,tEOR,t_delta;
    private double single_frame_time_second,single_frame_time_millis,single_frame_time_nanos;


   // private Rod rod;

    private List<Rod> rodList;
    private static final int NUMBER_OF_ROD=10;
    private int touchRodIndex;

    private Paint rodPaint;

    public TestLayout(Context context) {
        super(context);
        //setBackgroundColor(Color.parseColor("#159884"));
        this.context = context;
        this.frame_per_second =30;
        this.single_frame_time_second = 1/frame_per_second;
        this.single_frame_time_millis = single_frame_time_second*1000;
        this.single_frame_time_nanos = single_frame_time_millis*1000000;

        this.surfaceHolder = getHolder();
        //rod = new Rod(100,0,getScreenHeight());

        moveEventCalled = false;

        initRodlist();
        touchRodIndex=-1;
    }

    private void initRodlist() {
        rodList = new ArrayList<>();

        float spacing = getScreenWidth()/(NUMBER_OF_ROD+1);

        for(int i =0; i<NUMBER_OF_ROD;i++){
            Rod rod = new Rod(context,(i+1)*spacing,0,getScreenHeight());
            rodList.add(rod);
        }



    }

    private void initPaint(){
        rodPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rodPaint.setColor(Color.parseColor("#DD2C00"));
        rodPaint.setStyle(Paint.Style.STROKE);
        rodPaint.setStrokeWidth(12);
    }

    @Override
    public void run() {

        initPaint();

        tLF = System.nanoTime();
        t_delta = 0;

        while (canDraw){
            //Carry out Some Drawing

            updateDelta(t_delta);

            if(!surfaceHolder.getSurface().isValid()){
                // If Surface holder not Valid Continue the Loop
                continue;
            }

            draw();
            // Now Calculate EOR
            tEOR = System.nanoTime();

            // Now Calculate Delta timew
            t_delta = single_frame_time_nanos-(tEOR-tLF);

            // Now Sleep the Thread for Delta Time

            try {
                if(t_delta>0){
                    Thread.sleep((long) t_delta/1000000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tLF = System.nanoTime();


        }

    }

    private void updateDelta(double t_delta) {
        //rod.move();

        for(Rod x: rodList){
            x.move();
        }

        moveEventCalled=false;
    }

    private void draw(){


        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);

        //canvas.drawLine();
        //rod.draw(canvas);
        drawAllRod();
        canvas.drawLine(rodList.get(0).getRefX(),rodList.get(0).getDividerY(),rodList.get(NUMBER_OF_ROD-1).getRefX(),rodList.get(0).getDividerY(),rodPaint);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }

    private void drawAllRod() {
        for(Rod x: rodList){
            x.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value= super.onTouchEvent(event);

        if(!moveEventCalled){

            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN:{


                    return true;
                }

                case MotionEvent.ACTION_MOVE:{

                    float x= event.getX();
                    float y= event.getY();
                    // rod.check(x,y);
                    detectWhichRodIsTouched(x,y);
                }
            }

        }



        return value;
    }

    private void detectWhichRodIsTouched(float x, float y) {


       for(Rod rod:rodList){
           rod.check(x,y);
           /*if(Math.abs(rod.getRefX()-x)<30){
               touchRodIndex = rodList.indexOf(rod);
               break;
           }*/
       }
        //set move Event Called=true sothat it will not Called Again
        moveEventCalled=true;
    }


    public void pause(){
        canDraw=false;

        while (true){
            try {
                thread.join();
                // After Join Break the Loop
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        thread=null;


    }

    public void resume(){
        canDraw = true;
        thread = new Thread(this);
        thread.start();

    }




    private int toPx(int dp){
        return (int)((getResources().getDisplayMetrics().density)*dp);
    }

    private int getScreenHeight() {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int height = metrics.heightPixels;
        return height;
    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        return width;
    }

}
