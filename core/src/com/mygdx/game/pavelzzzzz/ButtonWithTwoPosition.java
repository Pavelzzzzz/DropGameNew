package com.mygdx.game.pavelzzzzz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Pavel on 04.12.16.
 */

public class ButtonWithTwoPosition {
    private Texture buttonImageOn;
    private Texture buttonImageOff;
    private boolean statusOn;
    private Rectangle rectangle;

    public ButtonWithTwoPosition(String nameImageOn, String nameImageOff, int x, int y, int width, int height){
        buttonImageOn = new Texture(nameImageOn);
        buttonImageOff = new Texture(nameImageOff);
        statusOn = true;
        rectangle = new Rectangle(x , y, width, height);
    }

    public boolean contains (float x, float y){
        if (rectangle.contains(x, y)){
            return true;
        }else {
            return false;
        }
    }

    public Texture getButtonImage(){
        if (statusOn){
            return buttonImageOn;
        }else {
            return buttonImageOff;
        }
    }

    public void setStatus(boolean bool){
        statusOn = bool;
    }

    public float[] getArrayData(){
        return new float []{rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()};
    }

    public void dispose() {
        buttonImageOn.dispose();
        buttonImageOn.dispose();
    }
}

