package com.mygdx.game.pavelzzzzz.model.items;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Pavel on 28.11.16.
 */

public class DropsCreateSpeed {

    private long createSpeedTime;
    private long quicklyCreateSpeedTime;
    private long nextDropCreateSpeedTime;
    private boolean quickly;

    public DropsCreateSpeed(){
        createSpeedTime =  1000000000;
        quickly = false;
        nextDropCreateSpeedTime = TimeUtils.nanoTime() + 10000000000L;
    }

    public void setQuicklyCreateSpeedTime(){
        quicklyCreateSpeedTime = TimeUtils.nanoTime() + 10000000000L;
    }

    public void update(){

        if ((quicklyCreateSpeedTime > TimeUtils.nanoTime()) && !quickly){
            createSpeedTime /= 2;
            quickly = true;
        } else if ((quicklyCreateSpeedTime < TimeUtils.nanoTime()) && quickly){
            createSpeedTime *= 2;
            quickly = false;
        }

        if ((nextDropCreateSpeedTime < TimeUtils.nanoTime()) && createSpeedTime > 250000000){
            createSpeedTime -= 100000;
            nextDropCreateSpeedTime = TimeUtils.nanoTime() + 10000000000L;
        }
    }

    public long getCreateSpeedTime(){
        return createSpeedTime;
    }
}

