package com.mygdx.game.pavelzzzzz.model.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.pavelzzzzz.ScreenManager;
import com.mygdx.game.pavelzzzzz.model.buttons.Button;
import com.mygdx.game.pavelzzzzz.model.database.ActionForSQLite;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;

import java.sql.SQLException;

/**
 * Created by Pavel on 04.12.16.
 */

public class ResultsScreen implements Screen {

    private ScreenManager screenManager;

    private Button buttonArrow;

    private Vector3 touchPoint;

    private DrawingString title;
    private DrawingString hat;
    private DrawingString results;

    public ResultsScreen(ScreenManager inputScreenManager) {

        screenManager = inputScreenManager;

        touchPoint = new Vector3();
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);

        title = new DrawingString("Results", 320, 475);

        hat = new DrawingString(String.format("%15s %10s %20s %15s", "Score", "Drop", "Creation time", "Speed"), 25, 400);

        update();

        System.out.print("ResultsScreen created\n");
    }

    public void update(){
        try{
            ActionForSQLite.connection();
            ActionForSQLite.createDB();
            results = new DrawingString(ActionForSQLite.readTenBestNomeDB(), 25, 360);
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

        screenManager.view().printStr64(title);
        screenManager.view().printStr32(hat);
        screenManager.view().printStr32(results);

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
