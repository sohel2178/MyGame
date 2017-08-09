package com.baudiabatash.mygame.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genius 03 on 8/9/2017.
 */

public class Rod {
    private static final float MARGIN=10;
    private static final float TEXI_BOX_SIZE=80;
    private static final float BEAD_RADIUS=40;
    private float refX,refY;
    private float height;
    private float availableHeight;
    private Paint boxPaint,rodPaint;

    private float rodHeight;

    private HeavenBead heavenBead;
    private float heavenBeadSpace,earthBeadSpace;

    private List<EarthBead> earthBeadList;

    public Rod(float refX, float refY, float height) {
        this.refX = refX;
        this.refY = refY;
        this.height = height;
        this.availableHeight = height-2*MARGIN;

        this.rodHeight = availableHeight-TEXI_BOX_SIZE;
        heavenBeadSpace = (rodHeight/4)-5;
        earthBeadSpace = rodHeight-heavenBeadSpace-5;

        float heavenBeadCy = refY+MARGIN+TEXI_BOX_SIZE+BEAD_RADIUS;

        heavenBead = new HeavenBead(refX,heavenBeadCy,heavenBeadSpace-2*BEAD_RADIUS);

        float earthBeadDisplacement=earthBeadSpace-8*BEAD_RADIUS;

        initEarhBeadList(earthBeadDisplacement);

        initPaint();
    }

    private void initEarhBeadList(float earthBeadDisplacement) {
        earthBeadList = new ArrayList<>();

        float earthBeadFirstRef = refY+MARGIN+TEXI_BOX_SIZE+heavenBeadSpace+10+earthBeadDisplacement+BEAD_RADIUS;

        for (int i=0;i<4;i++){
            EarthBead earthBead = new EarthBead(refX,2*i*BEAD_RADIUS+earthBeadFirstRef,earthBeadDisplacement);
            earthBeadList.add(earthBead);
        }
    }

    private void initPaint() {
        boxPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        boxPaint.setColor(Color.BLACK);
        boxPaint.setStyle(Paint.Style.STROKE);
        boxPaint.setStrokeWidth(5);

        rodPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rodPaint.setColor(Color.RED);
        rodPaint.setStyle(Paint.Style.STROKE);
        rodPaint.setStrokeWidth(20);
    }

    public void draw(Canvas canvas){
        canvas.drawRect((refX-TEXI_BOX_SIZE/2),refY+MARGIN,  (refX+TEXI_BOX_SIZE/2),refY+MARGIN+TEXI_BOX_SIZE,boxPaint);
        // Now draw the Rod on the Canvas
        canvas.drawLine(refX,refY+MARGIN+TEXI_BOX_SIZE,refX,refY+height-MARGIN,rodPaint);
        heavenBead.draw(canvas);

        for(EarthBead x: earthBeadList){
            x.draw(canvas);
        }
    }

    public void move(){

        for (EarthBead x: earthBeadList){
            x.move();
        }

        heavenBead.move();


    }

    public void check(float x, float y){
        EarthBead earthBead = getTouchedEarthBead(x,y);

        if(earthBead!=null){
            if(earthBead instanceof HeavenBead){
                // Logic for HeavenBead
            }else{
                // Logic for earthBead
                int beadIndex = earthBeadList.indexOf(earthBead);

                if(y-earthBead.getcY()>20){
                    moveBeadDown(beadIndex);

                }else if(y-earthBead.getcY()<-20){
                    moveBeadUp(beadIndex);
                }
            }
        }
    }

    private void moveBeadDown(int beadIndex){

        for(EarthBead x: earthBeadList){
            if(earthBeadList.indexOf(x)>=beadIndex){
                x.setMoveState(1);
            }
        }
    }

    private void moveBeadUp(int beadIndex){

        for(EarthBead x: earthBeadList){
            if(earthBeadList.indexOf(x)<=beadIndex){
                x.setMoveState(-1);
            }
        }
    }

    private EarthBead getTouchedEarthBead(float x, float y) {
        EarthBead bead = null;

        for(EarthBead eb:earthBeadList){
            float ebDist = (float) Math.sqrt(Math.pow(eb.getcX()-x,2)+Math.pow(eb.getcY()-y,2));

            if(ebDist<eb.getRadius()){
                bead = eb;
                break;
            }
        }

        if(bead==null){
            float ebDist = (float) Math.sqrt(Math.pow(heavenBead.getcX()-x,2)+Math.pow(heavenBead.getcY()-y,2));

            if(ebDist<heavenBead.getRadius()){
                bead = heavenBead;
            }
        }

        return bead;
    }
}
