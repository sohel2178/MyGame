package com.baudiabatash.mygame.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.baudiabatash.mygame.R;

/**
 * Created by Genius 03 on 8/9/2017.
 */

public class HeavenBead extends EarthBead {
    private static final int INCREMENT=25;


    public HeavenBead(Context context,float cX, float cY, float displacement) {
        super(context,cX, cY, displacement);
        getPaint().setColor(ContextCompat.getColor(context, R.color.heavenbeadColor));
    }

    public HeavenBead(Context context,float cX, float cY) {
        this(context,cX, cY,150);
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
                    setcY(getcY()+INCREMENT);
                }else{
                    setcY(getInitialCy()+getDisplacement());
                    //setMoveState(0);
                }

            }else{

                setTouch(false);

                if(getcY()<=getInitialCy()){
                    setcY(getInitialCy());
                    //setMoveState(0);

                }else{
                    setcY(getcY()-INCREMENT);
                }



            }
        }
    }
}
