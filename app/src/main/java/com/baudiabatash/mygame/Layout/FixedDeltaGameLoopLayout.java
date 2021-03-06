package com.baudiabatash.mygame.Layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.baudiabatash.mygame.R;

/**
 * Created by Genius 03 on 7/15/2017.
 */

public class FixedDeltaGameLoopLayout extends SurfaceView implements Runnable,SurfaceHolder.Callback {
    private Thread thread = null;
    private boolean canDraw=false;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    private int canvasWidth;
    private int canvasHeight;

    Bitmap bitmap;

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

    public FixedDeltaGameLoopLayout(Context context) {
        super(context);
        //setBackgroundColor(Color.parseColor("#159884"));
        this.frame_per_second =20;
        this.single_frame_time_second = 1/frame_per_second;
        this.single_frame_time_millis = single_frame_time_second*1000;
        this.single_frame_time_nanos = single_frame_time_millis*1000000;
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.strokeWidth=10;
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

    }

    private void draw(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.check);

        bitmap = Bitmap.createScaledBitmap(bitmap,getWidth(),getHeight(),true);

        canvas = surfaceHolder.lockCanvas();
        canvas.drawBitmap(bitmap,0,0,null);
        canvas.drawCircle(canvasWidth,canvasHeight,350,red_fill);
        canvas.drawCircle(0,canvasHeight,350,green_fill);
        canvas.drawCircle(0,0,350,blue_fill);
        canvas.drawCircle(canvasWidth,0,350,green_fill);
        surfaceHolder.unlockCanvasAndPost(canvas);

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
        Log.d(LOG,"frame_per_second "+frame_per_second);
        Log.d(LOG,"single_frame_time_second "+single_frame_time_second);
        Log.d(LOG,"single_frame_time_millis "+single_frame_time_millis);
        Log.d(LOG,"single_frame_time_nanos "+single_frame_time_nanos);
        Log.d(LOG,"tLF "+tLF);
        Log.d(LOG,"tEOR "+tEOR);
        Log.d(LOG,"t_delta "+t_delta);
    }


    private int toPx(int dp){
        return (int)((getResources().getDisplayMetrics().density)*dp);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.canvasWidth = width;
        this.canvasHeight = height;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
