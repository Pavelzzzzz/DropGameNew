package com.mygdx.game.pavelzzzzz.model.items.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingObject;

/**
 * Created by Pavel on 27.11.16.
 */

public class Basket implements DrawingObject {

    private Texture bucketImage;
    private Texture basinImage;
    private Texture currentImage;
    private Rectangle rectangle;
    private long basinTime;
    private boolean bucket;

    public Basket (){
        bucketImage = new Texture("bucket_512.png");
        basinImage = new Texture("basin900_435.png");
        currentImage = bucketImage;
        rectangle = new Rectangle();
        rectangle.x = 800 / 2 - 64 / 2;
        rectangle.y = 2;
        rectangle.width = 64;
        rectangle.height = 64;
        bucket = true;
    }

    public void setBasinTime (){
        basinTime = TimeUtils.nanoTime() + 10000000000L;
    }

    public void update(){
        if (((basinTime > TimeUtils.nanoTime()) && bucket)){
            currentImage = basinImage;
            rectangle.width = 128;
            bucket = false;
        } else if (((basinTime < TimeUtils.nanoTime()) && !bucket)){
            currentImage = bucketImage;
            rectangle.width = 64;
            bucket = true;
        }
    }
    @Override
    public Texture getImage(){
        return currentImage;
    }

    @Override
    public float getX() {
        return rectangle.x;
    }

    @Override
    public float getY() {
        return rectangle.y;
    }

    @Override
    public float getWidth() {
        return rectangle.width;
    }

    @Override
    public float getHeight() {
        return rectangle.height;
    }

    public void setRectangleX(float x){
        rectangle.x = x;
    }

    public void setRectangleY(float y){
        rectangle.y =y ;
    }

    public boolean overlaps(Rectangle x ){
        return rectangle.overlaps(x);
    }

    public void dispose(){
        bucketImage.dispose();
        basinImage.dispose();
        currentImage.dispose();
    }





}
