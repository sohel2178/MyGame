package com.baudiabatash.mygame.Layout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import com.baudiabatash.mygame.Model.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genius 03 on 8/5/2017.
 */

public class SudokuView extends View {
    private Context context;
    private int canvasWidth,canvasHeight;

    private int margin;
    private int side;

    private int[][] elementArray = new int[9][9];
    private List<Element> elementList;

    public SudokuView(Context context) {
        super(context);
        this.context=context;

        initElementArray();

        Log.d("HHH","Element Size = "+elementList.size());
    }

    private void initElementArray() {
        elementList = new ArrayList<>();
        for(int i=0;i<elementArray.length;i++){

            for(int j=0;j<elementArray[i].length;j++){
                Element element = new Element(i,j);
                elementList.add(element);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvasWidth = canvas.getWidth();
        margin = calculateMargin();
        side = calculateSide();

        Log.d("HHH","Margin = "+margin);
        Log.d("HHH","Side = "+side);

        for(Element x:elementList){
            x.draw(canvas,margin,side);
        }
    }

    private int calculateSide() {
        return (canvasWidth-2*margin)/9;
    }

    private int calculateMargin() {
        int tempMargin = (canvasWidth%9)/2;

        if(tempMargin<9){
            int minSide = canvasWidth-9*2;
            int remainder = minSide%9;
            tempMargin = 9+remainder/2;
        }
        return tempMargin;
    }
}
