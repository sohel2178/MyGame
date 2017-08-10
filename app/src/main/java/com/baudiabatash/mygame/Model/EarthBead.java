package com.baudiabatash.mygame.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.baudiabatash.mygame.R;

/**
 * Created by Sohel on 8/8/2017.
 */

public class EarthBead {
    private static final float RADIUS=55;
    private static final int THRESHOLD=20;
    private static final int INCREMENT=25;

    private float cX,cY;
    private float displacement;
    private float initialCy;

    private Paint paint;

    private int moveState;
    private boolean isTouch;

    private Context context;


    public EarthBead(Context context,float cX, float cY, float displacement) {
        this.context=context;
        this.cX = cX;
        this.cY = cY;
        this.displacement = displacement;
        this.initialCy = cY;
        moveState= 0;
        isTouch = false;

        initPaint();
    }

    public EarthBead(Context context,float cX, float cY) {
        this(context,cX,cY,200);
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(context, R.color.earthbeadColor));
    }

    public float getcX() {
        return cX;
    }

    public float getcY() {
        return cY;
    }

    public float getRadius(){
        return RADIUS;
    }


    public void check(float x, float y){
        float ebDist = (float) Math.sqrt(Math.pow(cX-x,2)+Math.pow(cY-y,2));

        if(ebDist<=RADIUS){

            if(y-cY>=THRESHOLD){
                moveState=1;
            }else if(y-cY<-THRESHOLD){
                moveState=-1;
            }
        }
    }


    public void draw(Canvas canvas){
        canvas.drawCircle(cX,cY,RADIUS,paint);

    }

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    public int getValue(){
        int value = 0;
        if(isTouch){
           value=1;
        }

        return value;

    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
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

                if(cY<initialCy){
                    cY=cY+INCREMENT;
                }else{
                    cY=initialCy;
                    // For Stoping Update set Move State =0
                    //moveState=0;
                }

                isTouch=false;


            }else{


                if(cY<=initialCy-displacement){
                    cY = initialCy-displacement;
                    // For Stoping Update set Move State =0
                    //moveState =0;
                }else{
                    cY=cY-INCREMENT;
                }
                isTouch = true;

            }
        }
    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }

    public float getInitialCy() {
        return initialCy;
    }

    public void setInitialCy(float initialCy) {
        this.initialCy = initialCy;
    }

    public void setcX(float cX) {
        this.cX = cX;
    }

    public void setcY(float cY) {
        this.cY = cY;
    }

    private int toPx(int dp){
        return (int)((context.getResources().getDisplayMetrics().density)*dp);
    }


}
