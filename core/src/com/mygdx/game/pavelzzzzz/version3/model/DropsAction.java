package com.mygdx.game.pavelzzzzz.version3.model;

import com.mygdx.game.pavelzzzzz.version3.DropDeclining;
import com.mygdx.game.pavelzzzzz.version3.DropsCreateSpeed;
import com.mygdx.game.pavelzzzzz.version3.GameScreen3;

/**
 * Created by Pavel on 27.11.16.
 */

public class DropsAction {

    DropDeclining dropDeclining;
    Basket basket;
    DropsCreateSpeed dropsCreateSpeed;
    GameScreen3 gameScreen3;

    public DropsAction(GameScreen3 inputGameScreen3, Basket inputbasket, DropDeclining inputDropDeclining, DropsCreateSpeed inputDropsCreateSpeed){
        gameScreen3 = inputGameScreen3;
        basket = inputbasket;
        dropDeclining = inputDropDeclining;
        dropsCreateSpeed = inputDropsCreateSpeed;
    }

    public void speedAdd(){
        dropDeclining.setAddDropSpeed();
    }

    public void changeCreateSpeed(){
        dropsCreateSpeed.setQuicklyCreateSpeedTime();
    }

    public void basin(){
        basket.setBasinTime();
    }

    public void lifeSubtraction(){
        gameScreen3.lifeSubtraction();
    }
}