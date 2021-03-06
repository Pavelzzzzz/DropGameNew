package com.mygdx.game.pavelzzzzz.model.items.model.drop;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.pavelzzzzz.model.items.model.DropsAction;

/**
 * Created by Pavel on 05.12.16.
 */

public class DropPoison extends DropSimple {


    public DropPoison(int i, Texture texture, DropsAction inputDropsAction) {
        super(i, texture, inputDropsAction);
    }

    @Override
    public void action() {
        dropsAction.lifeSubtraction();
    }
}
