package com.mygdx.game.pavelzzzzz.realisation.model;

/**
 * Created by Pavel on 27.11.16.
 */

public class DropsAction {

    com.mygdx.game.pavelzzzzz.realisation.DropDeclining dropDeclining;
    Basket basket;
    com.mygdx.game.pavelzzzzz.realisation.DropsCreateSpeed dropsCreateSpeed;
    com.mygdx.game.pavelzzzzz.realisation.GameScreen3 gameScreen3;

    public DropsAction(com.mygdx.game.pavelzzzzz.realisation.GameScreen3 inputGameScreen3, Basket inputbasket, com.mygdx.game.pavelzzzzz.realisation.DropDeclining inputDropDeclining, com.mygdx.game.pavelzzzzz.realisation.DropsCreateSpeed inputDropsCreateSpeed){
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