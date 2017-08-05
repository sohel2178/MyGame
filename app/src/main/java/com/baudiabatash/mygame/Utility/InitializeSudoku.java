package com.baudiabatash.mygame.Utility;

import com.baudiabatash.mygame.Model.Element;

import java.util.List;
import java.util.Random;

/**
 * Created by Genius 03 on 8/5/2017.
 */

public class InitializeSudoku {
    private List<Element> elementList;
    private int level;
    private Random random;

    public InitializeSudoku(List<Element> elementList, int level) {
        this.elementList = elementList;
        this.level = level;
        this.random = new Random();
    }

    public void run(){
        int i=0;
        int rowId=-1;
        int colId=-1;
        while (i<level){
            rowId=random.nextInt(9);
            colId=random.nextInt(9);

            if(i==0){
                Element element = getElementByRowandColumnId(rowId,colId);
                element.setValue(String.valueOf(random.nextInt(9)+1));

            }else{

                if(hasValue(rowId,colId)){
                    continue;
                }

                Element element = getElementByRowandColumnId(rowId,colId);
                element.setValue(String.valueOf(random.nextInt(9)+1));

            }
            i++;

        }
    }

    private boolean hasValue(int rowId,int colId){
        boolean retBool= false;
        Element element = getElementByRowandColumnId(rowId,colId);
        if(!element.getValue().equals("")){
            retBool=true;
        }

        return retBool;
    }

    private Element getElementByRowandColumnId(int rowId,int columnId){
        Element element=null;
        for(Element x:elementList){
            if(x.getRowId()==rowId && x.getColumnId()==columnId){
                element=x;
                break;
            }
        }
        return element;
    }
}
