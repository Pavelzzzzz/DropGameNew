package com.mygdx.game.pavelzzzzz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Pavel on 06.11.16.
 */

public class Drop extends Game {

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new com.mygdx.game.pavelzzzzz.Screens.MainMenuScreen2(this, null));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        super.getScreen().dispose();
    }
}
