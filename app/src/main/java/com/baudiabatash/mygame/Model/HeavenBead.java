package com.baudiabatash.mygame.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Genius 03 on 8/9/2017.
 */

public class HeavenBead extends EarthBead {


    public HeavenBead(float cX, float cY, float displacement) {
        super(cX, cY, displacement);
        getPaint().setColor(Color.BLACK);
    }

    public HeavenBead(float cX, float cY) {
        this(cX, cY,150);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public int getValue() {
        if(isTouch()){
            return 5;
        }else{
            return 0;
        }
    }

    @Override
    public void move() {
        if(getMoveState()!=0){
            if(getMoveState()==1){
                setTouch(true);

                if(getcY()<getInitialCy()+getDisplacement()){
                    setcY(getcY()+25);
                }else{
                    setcY(getInitialCy()+getDisplacement());
                }



                //Log.d("GGG","HUM");




            }else{

                setTouch(false);

                if(getcY()<=getInitialCy()){
                    setcY(getInitialCy());

                }else{
                    setcY(getcY()-25);
                }



            }
        }
    }
}
