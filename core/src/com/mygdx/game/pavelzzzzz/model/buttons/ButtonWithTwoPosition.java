package com.mygdx.game.pavelzzzzz.model.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.pavelzzzzz.model.draw.DrawingObject;

/**
 * Created by Pavel on 04.12.16.
 */

public class ButtonWithTwoPosition implements DrawingObject{
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

    public void setStatus(boolean bool){
        statusOn = bool;
    }

    public void dispose() {
        buttonImageOn.dispose();
        buttonImageOn.dispose();
    }

    @Override
    public Texture getImage() {
        if (statusOn){
            return buttonImageOn;
        }else {
            return buttonImageOff;
        }
    }

    @Override
    public float getX() {
        return rectangle.getX();
    }

    @Override
    public float getY() {
        return rectangle.getY();
    }

    @Override
    public float getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public float getHeight() {
        return rectangle.getHeight();
    }
}

