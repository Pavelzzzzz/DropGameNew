package com.mygdx.game.pavelzzzzz.model.items.model;

import com.mygdx.game.pavelzzzzz.model.items.DropDeclining;
import com.mygdx.game.pavelzzzzz.model.items.DropsCreateSpeed;
import com.mygdx.game.pavelzzzzz.model.screens.GameScreen;

/**
 * Created by Pavel on 27.11.16.
 */

public class DropsAction {

    DropDeclining dropDeclining;
    Basket basket;
    DropsCreateSpeed dropsCreateSpeed;
    GameScreen gameScreen3;

    public DropsAction(GameScreen inputGameScreen3, Basket inputbasket, DropDeclining inputDropDeclining, DropsCreateSpeed inputDropsCreateSpeed){
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