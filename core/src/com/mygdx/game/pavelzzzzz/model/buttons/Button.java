package com.mygdx.game.pavelzzzzz.model.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.pavelzzzzz.model.draw.DrawingObject;

/**
 * Created by Pavel on 04.12.16.
 */

public class Button implements DrawingObject{
    private Texture buttonImage;
    private Rectangle rectangle;

    public Button(String imageName, int x, int y, int width, int height){
        buttonImage = new Texture(imageName);
        rectangle = new Rectangle(x , y, width, height);
    }

    public boolean contains (float x, float y){
        if (rectangle.contains(x, y)){
            return true;
        }else {
            return false;
        }
    }

    public void dispose() {
        buttonImage.dispose();
    }

    @Override
    public Texture getImage() {
        return buttonImage;
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
