package com.mygdx.game.pavelzzzzz.version3;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Pavel on 27.11.16.
 */

public class DropDeclining {

    private int dropSpeed;
    private long doubleSpeedTime;
    private long nextDropSpeedTime;
    private boolean x2;

    public DropDeclining(){
        dropSpeed = 200;
        x2 = false;
        nextDropSpeedTime = TimeUtils.nanoTime() + 10000000000L;
    }

    public void setAddDropSpeed(){
        doubleSpeedTime = TimeUtils.nanoTime() + 10000000000L;
    }

    public void update(){

        if ((doubleSpeedTime > TimeUtils.nanoTime()) && !x2){
            dropSpeed += 100;
            x2 = true;
        } else if ((doubleSpeedTime < TimeUtils.nanoTime()) && x2){
            dropSpeed -= 100;
            x2 = false;
        }

        if (nextDropSpeedTime  < TimeUtils.nanoTime()){
            dropSpeed += 10;
            nextDropSpeedTime = TimeUtils.nanoTime() + 10000000000L;
        }

    }

    public int getDropSpeed(){
        return dropSpeed;
    }
}
