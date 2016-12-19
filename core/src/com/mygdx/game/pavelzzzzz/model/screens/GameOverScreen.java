package com.mygdx.game.pavelzzzzz.model.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.pavelzzzzz.ScreenManager;
import com.mygdx.game.pavelzzzzz.model.buttons.Button;
import com.mygdx.game.pavelzzzzz.model.database.ActionForSQLite;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;

import java.sql.SQLException;

/**
 * Created by Pavel on 30.11.16.
 */

public class GameOverScreen implements Screen {

    private ScreenManager screenManager;

    private Vector3 touchPoint;

    private Array<DrawingString> arrayString;

    private Button buttonPlay;
    private Button buttonArrow;

    public GameOverScreen(ScreenManager inputScreenManager){
        screenManager = inputScreenManager;

        buttonPlay = new Button("button/buttonPlayAgain.png",240, 350, 320, 100);
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);
        touchPoint = new Vector3();

        System.out.print("GameOverScreen created\n");
    }

    public void update(int inputResultArray[]){
        arrayString = new Array<DrawingString>();
        arrayString.add(new DrawingString("Collected drops: " + inputResultArray[0], 100, 300));
        arrayString.add(new DrawingString("Score: " + inputResultArray[1], 100, 250));
        arrayString.add(new DrawingString("Creation time: " + inputResultArray[2], 100, 200));
        arrayString.add(new DrawingString("Decline speed: " + inputResultArray[3], 100, 150));

        try{
            ActionForSQLite.connection();
            ActionForSQLite.createDB();
            ActionForSQLite.noteAddDB(inputResultArray[1], inputResultArray[0], inputResultArray[2], inputResultArray[3]);
            ActionForSQLite.CloseDB();
        }
        catch (ClassNotFoundException x){
            System.out.print("ClassNotFoundException\n");
        }
        catch (SQLException x){
            System.out.print("SQLException\n");
        }
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
