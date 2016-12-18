package com.mygdx.game.pavelzzzzz.model.items.model.drop;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.pavelzzzzz.model.items.model.DropsAction;

/**
 * Created by Pavel on 26.11.16.
 */

public class DropSimple {

    private int score;
    private Texture dropImage;
    protected DropsAction dropsAction;

    public DropSimple(int i, Texture texture, DropsAction inputDropsAction) {
        score = i;
        dropImage = texture;
        dropsAction = inputDropsAction;
    }

    public void SimpleDrop(int scor, Texture Image){
        score = scor;
        dropImage = Image;
    }

    public int getScore (){
        return score;
    }

    public Texture getImage(){
        return dropImage;
    }

    public void dispose() {
        dropImage.dispose();
    }

    public void action(){ }
}
