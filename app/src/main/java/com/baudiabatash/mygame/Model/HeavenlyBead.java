package com.baudiabatash.mygame.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

/**
 * Created by Sohel on 8/6/2017.
 */

public class HeavenlyBead {
    private float upperLimit;
    private float lowerLimit;
    private boolean isTouch;
    private float cX;
    private float cY;
    private float radius;
    private Paint beadPaint;

    public HeavenlyBead(float cX, float cY,float radius) {
        this.cX = cX;
        this.cY = cY;
        this.radius = radius;
        this.isTouch=false;
        initPaint();
    }

    private void initPaint(){
        beadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        beadPaint.setStyle(Paint.Style.FILL);
        beadPaint.setColor(Color.parseColor("#009688"));
    }

    public float getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(float upperLimit) {
        this.upperLimit = upperLimit;
    }

    public float getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(float lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    public float getcX() {
        return cX;
    }

    public void setcX(float cX) {
        this.cX = cX;
    }

    public float getcY() {
        return cY;
    }

    public void setcY(float cY) {
        this.cY = cY;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(cX,cY,radius,beadPaint);
    }
}
