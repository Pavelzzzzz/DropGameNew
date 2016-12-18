package com.mygdx.game.pavelzzzzz.model.draw;

/**
 * Created by Pavel on 17.12.16.
 */

public class DrawingString {

    private String string;
    private float x;
    private float y;

    public DrawingString(String inputString, float inputX, float inputY){
       string = inputString;
        x = inputX;
        y = inputY;
    }

    public String getString(){
        return string;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }
}
