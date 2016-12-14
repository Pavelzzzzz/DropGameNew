package com.mygdx.game.pavelzzzzz.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Pavel on 04.12.16.
 */

public class Button {
    private Texture buttonImage;
    private Rectangle rectangle;

    public Button(String imageName, int x, int y, int width, int height){
        buttonImage = new Texture(imageName);
        rectangle = new Rectangle(x , y, width, height);
    }

    public Texture getButtonImage(){
        return buttonImage;
    }

    public float[] getArrayData(){
        return new float []{rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()};
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
}
