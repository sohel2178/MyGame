package com.baudiabatash.mygame.Utility;

import com.baudiabatash.mygame.Model.Element;

import java.util.ArrayList;
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
                //element.setValue(String.valueOf(random.nextInt(9)+1));
                setValue(element);

            }
            i++;

        }
    }

    private void setValue(Element element){
        int initialValue= random.nextInt(9)+1;
        boolean loopStarter= true;

        while (loopStarter){
            if(isValueAlreadyInTheBlock(element,initialValue) || isValueAlreadyInTheRow(element,initialValue) || isValueAlreadyInTheColumn(element,initialValue)){
                initialValue=(initialValue+1);
                if(initialValue!=9){
                    initialValue=initialValue%9;
                }
                continue;
            }
            element.setValue(String.valueOf(initialValue));
            loopStarter=false;
        }
    }

    private boolean isValueAlreadyInTheBlock(Element element,int value){
        boolean retBool = false;
        List<Element> blockElementList = getBlockList(element.getBlockId());

        for(Element x:blockElementList){
            if(x.getValue().equals(String.valueOf(value))){
                retBool=true;
                break;
            }
        }

        return retBool;
    }
    private boolean isValueAlreadyInTheRow(Element element,int value){
        boolean retBool = false;
        List<Element> rowElementList = getRowList(element.getRowId());

        for(Element x:rowElementList){
            if(x.getValue().equals(String.valueOf(value))){
                retBool=true;
                break;
            }
        }

        return retBool;
    }
    private boolean isValueAlreadyInTheColumn(Element element,int value){
        boolean retBool = false;
        List<Element> columnElementList = getColumnList(element.getColumnId());

        for(Element x:columnElementList){
            if(x.getValue().equals(String.valueOf(value))){
                retBool=true;
                break;
            }
        }

        return retBool;
    }

    private List<Element> getBlockList(int blockId){
        List<Element> retList = new ArrayList<>();
        int counter=0;

        for(Element x:elementList){
            if(x.getBlockId()==blockId){
                retList.add(x);
                counter++;
                if(counter==9){
                    break;
                }
            }
        }

        return retList;
    }
    private List<Element> getRowList(int rowId){
        List<Element> retList = new ArrayList<>();
        int counter=0;

        for(Element x:elementList){
            if(x.getRowId()==rowId){
                retList.add(x);
                counter++;
                if(counter==9){
                    break;
                }
            }
        }

        return retList;
    }
    private List<Element> getColumnList(int colId){
        List<Element> retList = new ArrayList<>();
        int counter=0;

        for(Element x:elementList){
            if(x.getColumnId()==colId){
                retList.add(x);
                counter++;
                if(counter==9){
                    break;
                }
            }
        }

        return retList;
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
