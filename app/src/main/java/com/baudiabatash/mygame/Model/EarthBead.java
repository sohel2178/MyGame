package com.baudiabatash.mygame.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Sohel on 8/8/2017.
 */

public class EarthBead {

    private float cX,cY,radius;
    private float displacement;
    private float initialCy;

    private Paint paint;

    private int moveState;

    public EarthBead(float cX, float cY, float radius) {
        this.cX = cX;
        this.cY = cY;
        this.radius = radius;
        this.displacement =200;
        this.initialCy = cY;

        moveState= 0;

        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(cX,cY,radius,paint);

    }


    public int getMoveState() {
        return moveState;
    }

    public void setMoveState(int moveState) {
        this.moveState = moveState;
    }

    public void move(){
        if(moveState!=0){
            if(moveState==1){

                if(cY<initialCy+displacement){
                    cY=cY+25;
                }else{
                    cY=initialCy+displacement;
                }


            }else{

                if(cY<=initialCy){
                    cY=initialCy;

                }else{
                    cY=cY-25;
                }

            }
        }
    }
}
