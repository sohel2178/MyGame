package com.baudiabatash.mygame.Model;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohel on 8/8/2017.
 */

public class EarthBeadGroup {
    private List<EarthBead> earthBeadList;
    private float refX,refY;

    public EarthBeadGroup(float refX, float refY){
        this.refX = refX;
        this.refY = refY;
        initList();
    }

    private void initList() {
        earthBeadList = new ArrayList<>();

        for(int i=0;i<4;i++) {
            EarthBead earthBead = new EarthBead(refX,refY+(2*i+1)*50,50);
            earthBeadList.add(earthBead);

        }
    }

    public void draw(Canvas canvas){
        for(EarthBead x :earthBeadList){
            x.draw(canvas);
        }
    }

    public void check(float x, float y){

        EarthBead bead = getTouchedBead(x,y);

        if(bead!=null){
            int beadIndex = earthBeadList.indexOf(bead);
            if(y-bead.getcY()>20){
                moveBeadDown(beadIndex);

            }else if(y-bead.getcY()<-20){
                moveBeadUp(beadIndex);
            }
        }



    }

    public void move(){
        for (EarthBead x: earthBeadList){
            x.move();
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

    private EarthBead getTouchedBead(float x, float y){
        EarthBead bead = null;

        for(EarthBead eb: earthBeadList){
            float ebDist = (float) Math.sqrt(Math.pow(eb.getcX()-x,2)+Math.pow(eb.getcY()-y,2));

            if(ebDist<eb.getRadius()){
                bead = eb;
                break;
            }
        }

        return bead;
    }


}
