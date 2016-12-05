package com.mygdx.game.pavelzzzzz.realisation.model.drop;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.pavelzzzzz.realisation.model.DropsAction;


/**
 * Created by Pavel on 28.11.16.
 */

public class DropNumber extends DropSimple {


    public DropNumber(int i, Texture texture, DropsAction inputDropsAction) {
        super(i, texture, inputDropsAction);
    }

    @Override
    public void action() {
        dropsAction.changeCreateSpeed();
    }
}
