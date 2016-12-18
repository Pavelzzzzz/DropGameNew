package com.mygdx.game.pavelzzzzz.model.draw;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Pavel on 17.12.16.
 */

public class DrawingImage implements DrawingObject {

    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;


    public DrawingImage(Texture inputTexture, float inputX, float inputY, float inputWidth, float inputHeight){
        texture = inputTexture;
        x = inputX;
        y = inputY;
        width = inputWidth;
        height = inputHeight;
    }

    @Override
    public Texture getImage() {
        return texture;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void dispose() {
        texture.dispose();
    }
}
