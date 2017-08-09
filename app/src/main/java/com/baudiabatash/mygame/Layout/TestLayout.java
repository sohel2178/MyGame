package com.baudiabatash.mygame.Layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.baudiabatash.mygame.Model.EarthBead;
import com.baudiabatash.mygame.Model.EarthBeadGroup;
import com.baudiabatash.mygame.Model.HeavenBead;
import com.baudiabatash.mygame.Model.Rod;
import com.baudiabatash.myutil.MyUtils;

/**
 * Created by Sohel on 8/6/2017.
 */

public class TestLayout extends SurfaceView implements Runnable {
    private Thread thread = null;
    private boolean canDraw=false;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Context context;

    private boolean isClicked;

    private float screenHeight;

    private int moveState;
    private float displacement;

    //Bitmap bitmap;

    private static final String LOG="SOHEL";

    // Different Paint Init Here
    private Paint red_fill,blue_fill,green_fill;
    private Paint red_stroke,blue_stroke,green_stroke;

    private float strokeWidth;

    //Game Loop Variable
    private double frame_per_second;
    private double tLF,tEOR,t_delta;
    private double single_frame_time_second,single_frame_time_millis,single_frame_time_nanos;

    // Rorational Field Variable
    //private double theta,theta_per_sec;


    //private EarthBead earthBead;
    //private EarthBeadGroup earthBeadGroup;
    //private HeavenBead heavenBead;

    private Rod rod;

    public TestLayout(Context context) {
        super(context);
        //setBackgroundColor(Color.parseColor("#159884"));
        this.context = context;
        this.frame_per_second =30;
        this.single_frame_time_second = 1/frame_per_second;
        this.single_frame_time_millis = single_frame_time_second*1000;
        this.single_frame_time_nanos = single_frame_time_millis*1000000;
        this.surfaceHolder = getHolder();
        this.strokeWidth=10;
        //this.theta =0;
        //this.theta_per_sec = Math.PI/500000000;


        isClicked=false;
        moveState=0;
        displacement=300;


        rod = new Rod(100,0,getScreenHeight());
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
        //theta = theta+theta_per_sec*t_delta;
        /*if(isClicked){
            mCy=mCy+5;
        }*/


        rod.move();

        //earthBeadGroup.move();



    }

    private void draw(){
        //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.check);

        //bitmap = Bitmap.createScaledBitmap(bitmap,getWidth(),getHeight(),true);

        canvas = surfaceHolder.lockCanvas();
        //canvas.drawBitmap(bitmap,0,0,null);
        canvas.drawColor(Color.WHITE);
        //canvas.drawCircle(getWidth()/2,getHeight()/2,200,red_stroke);
        //canvas.drawCircle(getWidth()/2,getHeight()/2-200,60,green_fill);
        //canvas.drawCircle(getWidth()/2,getHeight()/2+200,60,blue_fill);

        rod.draw(canvas);

        //canvas.drawText(String.valueOf((int)(theta/(2*Math.PI))),(getWidth()/2-30),(getHeight()/2-30),red_stroke);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value= super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{


                return true;

            }

            case MotionEvent.ACTION_MOVE:{
                float x= event.getX();
                float y= event.getY();

                //earthBeadGroup.check(x,y);

                rod.check(x,y);


            }
        }
        return value;
    }

    private void initPaint(){
        red_fill = new Paint(Paint.ANTI_ALIAS_FLAG);
        red_fill.setColor(Color.RED);
        red_fill.setStyle(Paint.Style.FILL);

        blue_fill = new Paint();
        blue_fill.setColor(Color.BLUE);
        blue_fill.setStyle(Paint.Style.FILL);

        green_fill = new Paint();
        green_fill.setColor(Color.GREEN);
        green_fill.setStyle(Paint.Style.FILL);

        red_stroke = new Paint();
        red_stroke.setColor(Color.RED);
        red_stroke.setTextSize(60);
        red_stroke.setStrokeWidth(5);
        red_stroke.setStyle(Paint.Style.STROKE);

        blue_stroke = new Paint();
        blue_stroke.setColor(Color.BLUE);
        blue_stroke.setStrokeWidth(strokeWidth);
        blue_stroke.setStyle(Paint.Style.STROKE);

        green_stroke = new Paint();
        green_stroke.setColor(Color.GREEN);
        green_stroke.setStrokeWidth(strokeWidth);
        green_stroke.setStyle(Paint.Style.STROKE);
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
        // Do the thing When Activity is Resume
        canDraw = true;
        thread = new Thread(this);
        thread.start();

    }

    private void stats(){
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

}
