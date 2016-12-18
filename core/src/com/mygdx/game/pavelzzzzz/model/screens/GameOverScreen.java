package com.mygdx.game.pavelzzzzz.model.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.pavelzzzzz.ScreenManager;
import com.mygdx.game.pavelzzzzz.model.buttons.Button;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;

/**
 * Created by Pavel on 30.11.16.
 */

public class GameOverScreen implements Screen {

    private ScreenManager screenManager;

    private int resultArray[];
    private Vector3 touchPoint;

    private Array<DrawingString> arrayString;

    private Button buttonPlay;
    private Button buttonArrow;

    public GameOverScreen(ScreenManager inputScreenManager, int inputResultArray[]){
        screenManager = inputScreenManager;

        resultArray = inputResultArray;
        buttonPlay = new Button("button/buttonPlayAgain.png",240, 350, 320, 100);
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);
        touchPoint = new Vector3();

        arrayString = new Array<DrawingString>();
        arrayString.add(new DrawingString("Collected drops: " + resultArray[0], 100, 300));
        arrayString.add(new DrawingString("Score: " + resultArray[1], 100, 250));
        arrayString.add(new DrawingString("Creation time: " + resultArray[2], 100, 200));
        arrayString.add(new DrawingString("Decline speed: " + resultArray[3], 100, 150));

//        Score score = new Score();
//        StringBuilder sb = new StringBuilder();
//        sb.append("      ");
//        sb.append(Integer.toString(inputResultArray[0]));
//        sb.append("      ");
//        sb.append(Integer.toString(inputResultArray[2]));
//        sb.append("      ");
//        sb.append(Integer.toString(inputResultArray[3]));
//        sb.append("\n");
//        score.setScore(new String[]{Integer.toString(inputResultArray[1]), sb.toString()});
//        score.writeToFile();
        System.out.print("GameOverScreen created\n");
    }

    public void setResults(int[] array){
        resultArray = array;
        arrayString.clear();
        arrayString.add(new DrawingString("Collected drops: " + resultArray[0], 100, 300));
        arrayString.add(new DrawingString("Score: " + resultArray[1], 100, 250));
        arrayString.add(new DrawingString("Creation time: " + resultArray[2], 100, 200));
        arrayString.add(new DrawingString("Decline speed: " + resultArray[3], 100, 150));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        screenManager.view().screenClear(0, 0, 0.5f, 1);

        for (DrawingString i: arrayString){
            screenManager.view().printStr64(i);
        }
        screenManager.view().printImage(buttonPlay);
        screenManager.view().printImage(buttonArrow);

        touchPoint = screenManager.generationClickVector();
        if (touchPoint != null) {
            if (buttonPlay.contains(touchPoint.x, touchPoint.y)){
                screenManager.setGameScreen();
            }
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
    public void dispose() {;
        buttonPlay.dispose();
        buttonArrow.dispose();
        System.out.print("GameOverScreen disposed\n");
    }


}
