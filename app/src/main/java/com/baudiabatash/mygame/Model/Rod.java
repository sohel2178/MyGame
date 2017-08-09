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
    private static final int THRESHOLD=25;
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

    // declare a integer veriable that will tell us which elenemt is touched
    // set its value in check method
    // and take appropriate action in move method according to value


    public Rod(float refX, float refY, float height) {
        this.refX = refX;
        this.refY = refY;
        this.height = height;
        //init Touch element value equal 0. in that case no action will take
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
        boxPaint.setTextSize(40);

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

        canvas.drawText(String.valueOf(getValue()),refX-MARGIN,refY+MARGIN+TEXI_BOX_SIZE/2+MARGIN,boxPaint);
    }

    public void move(){

        for (EarthBead x: earthBeadList){
            //Log.d("YYYY","MOVE METHOD IS CALLED");
            x.move();
        }

        heavenBead.move();

    }

    public void check(float x, float y){
        EarthBead earthBead = getTouchedEarthBead(x,y);

        if(earthBead!=null){

            if(earthBead instanceof HeavenBead){
                //Set Touch Element so that its Only register heaven Bead
                if(y-earthBead.getcY()>THRESHOLD){
                    heavenBead.setMoveState(1);

                }else if(y-earthBead.getcY()<-THRESHOLD){
                    heavenBead.setMoveState(-1);

                }
            }else{
                // Logic for earthBead
                int beadIndex = earthBeadList.indexOf(earthBead);

                if(y-earthBead.getcY()>THRESHOLD){
                    moveBeadDown(beadIndex);


                }else if(y-earthBead.getcY()<-THRESHOLD){
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

    public int getValue(){
        int total = 0;
        for(EarthBead x: earthBeadList){
            total = total+x.getValue();
        }

        return total+heavenBead.getValue();
    }
}
