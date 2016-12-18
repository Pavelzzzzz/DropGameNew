package com.mygdx.game.pavelzzzzz.model.items.model.drop;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.pavelzzzzz.model.items.model.DropsAction;


/**
 * Created by Pavel on 27.11.16.
 */

public class DropBasket extends DropSimple {

    public DropBasket(int i, Texture texture, DropsAction inputDropsAction) {
        super(i, texture, inputDropsAction);
    }

    @Override
    public void action(){
        dropsAction.basin();
    }
}
