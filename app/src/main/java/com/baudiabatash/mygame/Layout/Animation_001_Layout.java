package com.baudiabatash.mygame.Layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.Log;
import android.view.View;

import com.baudiabatash.mygame.R;
import com.baudiabatash.mygame.Utility.AnimUtil;
import com.baudiabatash.myutil.MyUtils;

/**
 * Created by Genius 03 on 7/4/2017.
 */

public class Animation_001_Layout extends View {

    private Paint red_fill,blue_fill,green_fill;
    private Paint red_stroke,blue_stroke,green_stroke;
    private Paint mGradientFill;
    private float strokeWidth;
    private Context context;

    private Path triangle;

    private int rect001Height;

    private int deviceWidth,deviceHeight;

    private Bitmap beadBitmap;

    private int bead_x,bead_y;
    private int maxSizeOfBitMap;

    private int x_dir_movement,y_dir_movement;

    public Animation_001_Layout(Context context) {
        super(context);
        this.context = context;
        //setBackgroundResource(R.drawable.check);
        this.strokeWidth=10;
        this.rect001Height=300;
        deviceWidth = AnimUtil.getScreenWidth(context);
        deviceHeight = AnimUtil.getScreenHeight(context);

        Log.d("Width",deviceWidth+"");
        Log.d("Width",deviceHeight+"");
        bead_x = 450;
        bead_y = 750;

        maxSizeOfBitMap=100;

        // set movement
        x_dir_movement= 1;
        y_dir_movement= 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        // Draw a Rectangle
        Rect rect001 = new Rect((int)strokeWidth,(int)strokeWidth,(int)(deviceWidth-strokeWidth),rect001Height);
        canvas.drawRect(rect001,green_stroke);

        Rect rect002 = new Rect((int)(strokeWidth),rect001Height,(int)(deviceWidth-strokeWidth),500);
        canvas.drawRect(rect002,green_stroke);

        Rect rect003 = new Rect((int)(1.5*strokeWidth),(int)(rect001Height+0.5*strokeWidth),(int)(deviceWidth-strokeWidth-.5*strokeWidth),(int)(500-.5*strokeWidth));
        canvas.drawRect(rect003,red_fill);


        canvas.drawCircle(deviceWidth/2,600+strokeWidth,100,blue_stroke);
        canvas.drawCircle(deviceWidth/2,600+strokeWidth,(float)(100-0.5*strokeWidth),green_fill);

        triangle= new Path();
        triangle.moveTo(300,0);
        triangle.lineTo(600,0);
        triangle.lineTo(450,300);
        triangle.close();

        canvas.drawPath(triangle,red_fill);

        //beadBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.beads);

        //Bitmap resizedBitMap = getResizedBitmap(beadBitmap,maxSizeOfBitMap);



        bead_x = bead_x+x_dir_movement;
        bead_y = bead_y+y_dir_movement;

        canvas.drawCircle(bead_x,bead_y,50,mGradientFill);

        //canvas.drawBitmap(resizedBitMap,bead_x,bead_y,null);

        if(bead_x>=deviceWidth-50 || bead_x<=50){
            x_dir_movement=-1*x_dir_movement;
        }

        if(bead_y>=deviceHeight-50 || bead_y<=50){
            y_dir_movement=-1*y_dir_movement;
        }



        invalidate();


       // MyUtils.getScreenWidthInDp()
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

        mGradientFill = new Paint();
        mGradientFill.setColor(Color.BLACK);

        mGradientFill.setStrokeWidth(1);
        mGradientFill.setStyle(Paint.Style.FILL_AND_STROKE);
        mGradientFill.setShader(new RadialGradient(getWidth() / 2, getHeight() / 2,
                getHeight() / 3, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.MIRROR));
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
