package com.baudiabatash.mygame.Layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.baudiabatash.mygame.R;

/**
 * Created by Genius 03 on 8/6/2017.
 */

public class AbacusView extends View {
    private static final String TAG="AbacusView";
    private Context context;
    private int canvasWidth,canvasHeight;
    private Paint selectPaint,borderPaint,rodPaint;

    private int margin;
    private int effectiveHeight,effectiveWidth;

    private int dividerY,rodCenterToCenter;

    public AbacusView(Context context) {
        super(context);
        this.context = context;
    }


    private void initPaint(){
        selectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectPaint.setColor(Color.parseColor("#eedd82"));
        selectPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(ContextCompat.getColor(context, R.color.borderColor));
        borderPaint.setStrokeWidth(8);

        rodPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rodPaint.setStyle(Paint.Style.STROKE);
        rodPaint.setColor(ContextCompat.getColor(context, R.color.rodColor));
        rodPaint.setStrokeWidth(8);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        initPaint();

        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();

        margin = calculateMargin();
        effectiveHeight = calculateEffectiveHeight();
        effectiveWidth = calculateEffectiveWidth();

        // Draw Border on Canvas
        drawBorder(canvas);

        Log.d(TAG,"heigh "+canvasHeight);
        Log.d(TAG,"width "+canvasWidth);
        Log.d(TAG,"margin "+margin);
        Log.d(TAG,"effectiveHeight "+effectiveHeight);
    }

    private void drawBorder(Canvas canvas) {
        canvas.drawRect(margin,margin,canvas.getWidth()-margin,canvas.getHeight()-margin,borderPaint);
        dividerY = effectiveHeight/5;
        canvas.drawLine(margin,margin+dividerY,canvas.getWidth()-margin,margin+dividerY,borderPaint);
        rodCenterToCenter = effectiveWidth/9;

        canvas.drawLine(margin+rodCenterToCenter,margin,margin+rodCenterToCenter,canvas.getHeight()-margin,rodPaint);


    }

    private int calculateEffectiveHeight() {
        return (canvasHeight-2*margin);
    }

    private int calculateEffectiveWidth() {
        return (canvasWidth-2*margin);
    }

    private int calculateMargin() {
        int tempMargin = (canvasHeight%9)/2;

        if(tempMargin<9){
            int minSide = canvasHeight-9*2;
            int remainder = minSide%9;
            tempMargin = 9+remainder/2;
        }
        return tempMargin;
    }
}
