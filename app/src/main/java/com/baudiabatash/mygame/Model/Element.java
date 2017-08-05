package com.baudiabatash.mygame.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Genius 03 on 8/5/2017.
 */

public class Element {
    private int rowId;
    private int columnId;
    private String value;

    private Paint blackPaint;

    public Element(int rowId, int columnId, String value) {
        this.rowId = rowId;
        this.columnId = columnId;
        this.value = value;
        initPaint();


    }

    private void initPaint() {
        blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(8);
    }

    public Element(int rowId, int columnId) {
        this(rowId,columnId,"");
    }


    public void draw(Canvas canvas,int margin,int side){
        int startX= margin+side*columnId;
        int startY = margin+side*rowId;

        canvas.drawRect(startX,startY,(startX+side),(startY+side),blackPaint);
    }
}
