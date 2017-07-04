package com.baudiabatash.mygame.Layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.baudiabatash.mygame.R;
import com.baudiabatash.mygame.Utility.AnimUtil;

/**
 * Created by Genius 03 on 7/4/2017.
 */

public class Animation_002_Layout extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean canDraw=false;

    private Bitmap backgroundBitMap;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    // Paint
    private Paint red_fill,blue_fill,green_fill;
    private Paint red_stroke,blue_stroke,green_stroke;
    private Paint mGradientFill;
    private float strokeWidth;
    private Context context;

    private Path squarePath;

    public Animation_002_Layout(Context context) {
        super(context);

        surfaceHolder = getHolder();
        backgroundBitMap = AnimUtil.getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.check),AnimUtil.getScreenWidth(context)
            ,AnimUtil.getScreenHeight(context));

        this.strokeWidth=10;
    }

    @Override
    public void run() {
        // Initialize Paint
        initPaint();

        while (canDraw){
            //Carry out Some Drawing

            if(!surfaceHolder.getSurface().isValid()){
                // If Surface holder not Valid Continue the Loop
                continue;
            }
            // If Surface Holder is Valid rest of the Code below is Execute



            // Get the Canvas from Surface Holder
            canvas = surfaceHolder.lockCanvas();
            canvas.drawBitmap(backgroundBitMap,0,0,null);

            squarePath = createSquarePath(100,144,575);
            canvas.drawPath(squarePath,red_stroke);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    private Path createSquarePath(int x, int y , int side){
        Path path = new Path();
        path.moveTo(x,y);
        path.lineTo(x+side,y);
        path.lineTo(x+side,y+side);
        path.lineTo(x,y+side);
        path.close();

        return path;

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

    private void initPaint(){
        red_fill = new Paint();
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
        red_stroke.setStrokeWidth(strokeWidth);
        red_stroke.setStyle(Paint.Style.STROKE);
        blue_stroke = new Paint();
        blue_stroke.setColor(Color.BLUE);
        blue_stroke.setStrokeWidth(strokeWidth);
        blue_stroke.setStyle(Paint.Style.STROKE);
        green_stroke = new Paint();
        green_stroke.setColor(Color.GREEN);
        green_stroke.setStrokeWidth(strokeWidth);
        green_stroke.setStyle(Paint.Style.STROKE);

       /* mGradientFill = new Paint();
        mGradientFill.setColor(Color.BLACK);

        mGradientFill.setStrokeWidth(1);
        mGradientFill.setStyle(Paint.Style.FILL_AND_STROKE);
        mGradientFill.setShader(new RadialGradient(getWidth() / 2, getHeight() / 2,
                getHeight() / 3, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.MIRROR));*/
    }
}
