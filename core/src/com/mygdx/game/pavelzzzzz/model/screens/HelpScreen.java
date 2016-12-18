package com.mygdx.game.pavelzzzzz.model.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.pavelzzzzz.ScreenManager;

import com.mygdx.game.pavelzzzzz.model.draw.DrawingImage;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;
import com.mygdx.game.pavelzzzzz.model.buttons.Button;

/**
 * Created by Pavel on 17.12.16.
 */

public class HelpScreen implements Screen {

    private ScreenManager screenManager;

    private Button buttonArrow;

    private Array<DrawingImage> arrayImage;
    private Array<DrawingString> arrayString;
    private DrawingString title;

    private Vector3 touchPoint;

    public HelpScreen(ScreenManager inputScreenManager) {

        screenManager = inputScreenManager;

        touchPoint = new Vector3();
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);

        title = new DrawingString("Help screen", 250, 475);

        arrayImage = new Array<DrawingImage>();
        arrayImage.add(new DrawingImage(new Texture("dropBlue.png"), 110, 275, 64, 64));
        arrayImage.add(new DrawingImage(new Texture("dropTransparentGreen.png"), 110, 65, 64, 64));
        arrayImage.add(new DrawingImage(new Texture("dropOrange.png"), 110, 205, 64, 64));
        arrayImage.add(new DrawingImage(new Texture("dropPurple.png"), 110, 135, 64, 64));
        arrayImage.add(new DrawingImage(new Texture("dropLightGreenPoison.png"), 110, 0, 64, 64));

        arrayString = new Array<DrawingString>();
        arrayString.add(new DrawingString("Your main goal is to catch as many drops as you can.", 50, 400));
        arrayString.add(new DrawingString("Types of drops:", 80, 375));
        arrayString.add(new DrawingString(" ->  Score +1", 180, 325));
        arrayString.add(new DrawingString(" ->  Score +25; creation speed /2", 180, 255));
        arrayString.add(new DrawingString(" ->  Score +25; decline speed *2", 180, 185));
        arrayString.add(new DrawingString(" ->  Score +10; bucket's width *2", 180, 115));
        arrayString.add(new DrawingString(" ->  Score +0; life -1", 180, 45));

        System.out.print("HelpScreen created\n");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        screenManager.view().screenClear(0, 0, 0.5f, 1);

        screenManager.view().printStr64(title);

        for (DrawingImage curentImage: arrayImage){
            screenManager.view().printImage(curentImage);
        }

        for (DrawingString currentString: arrayString){
            screenManager.view().printStr32(currentString);
        }

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
        for (DrawingImage currentImage: arrayImage){
            currentImage.dispose();
        }
        System.out.print("HelpScreen disposed\n");
    }
}
