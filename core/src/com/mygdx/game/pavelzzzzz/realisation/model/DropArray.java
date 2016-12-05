package com.mygdx.game.pavelzzzzz.realisation.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.pavelzzzzz.realisation.model.drop.DropSimple;

/**
 * Created by Pavel on 26.11.16.
 */

public class DropArray {

    public int DropCount;
    private Array<Rectangle> rectDrops;
    private Array<DropSimple> typeDrops;


    public DropArray(){
        typeDrops = new Array<DropSimple>();
        rectDrops = new Array<Rectangle>();
    }

    public int size(){
        return rectDrops.size > typeDrops.size ? typeDrops.size : rectDrops.size;
    }

    public void set(Rectangle rect, DropSimple drop){
        rectDrops.add(rect);
        typeDrops.add(drop);
    }

    public Rectangle getRectangle(int ind){
        return rectDrops.get(ind);
    }

    public DropSimple getType(int ind){
        return typeDrops.get(ind);
    }

    public void setY(int ind, float y){
        rectDrops.get(ind).setY(y);
    }

    public void remove(int ind){
        typeDrops.removeIndex(ind);
        rectDrops.removeIndex(ind);
    }

}
