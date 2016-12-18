package com.mygdx.game.pavelzzzzz.model.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.pavelzzzzz.ScreenManager;
import com.mygdx.game.pavelzzzzz.model.buttons.Button;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;

/**
 * Created by Pavel on 04.12.16.
 */

public class ResultsScreen implements Screen {

    ScreenManager screenManager;

    private Button buttonArrow;

    private Vector3 touchPoint;

    private DrawingString title;

    public ResultsScreen(ScreenManager inputScreenManager) {

        screenManager = inputScreenManager;

        touchPoint = new Vector3();
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);

        title = new DrawingString("Results", 320, 460);

        System.out.print("ResultsScreen created\n");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        screenManager.view().screenClear(0, 0, 0.5f, 1);

        screenManager.view().printStr64(title);
        //String s1 = String.format("%10s %10s %10s %10s", "Score", "Drop", "Creation time", "Speed");
        //font1.draw(game.batch, s1, 150, 380);
//        font1.draw(game.batch, "Score  Drop  Speed  Creation time", 150, 380);
//        font1.draw(game.batch, results, 150, 330);
        screenManager.view().printImage(buttonArrow);

        touchPoint = screenManager.generationClickVector();
        if (touchPoint != null) {
            if (buttonArrow.contains(touchPoint.x, touchPoint.y)){
                screenManager.setMainMenuScreen();
            }}
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        buttonArrow.dispose();
        System.out.print("ResultScreen disposed\n");
    }
}
