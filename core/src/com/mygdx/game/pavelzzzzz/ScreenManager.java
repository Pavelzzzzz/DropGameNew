package com.mygdx.game.pavelzzzzz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.pavelzzzzz.model.screens.GameOverScreen;
import com.mygdx.game.pavelzzzzz.model.screens.GameScreen;
import com.mygdx.game.pavelzzzzz.model.screens.MainMenuScreen;
import com.mygdx.game.pavelzzzzz.model.screens.ResultsScreen;
import com.mygdx.game.pavelzzzzz.view.View;

import com.mygdx.game.pavelzzzzz.model.screens.HelpScreen;

/**
 * Created by Pavel on 17.12.16.
 */

public class ScreenManager extends Game {

    private View view;

    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private ResultsScreen resultsScreen;
    private HelpScreen helpScreen;

    private Array<Screen> delScreens;

    private Vector3 touchPoint;
    private boolean stateClick;

    @Override
    public void create() {
        delScreens = new Array<Screen>();
        view = new View();
        touchPoint = new Vector3();
        setMainMenuScreen();
        System.out.print("ScreenManager created\n");
    }

    public Vector3 generationClickVector(){
        if (Gdx.input.isTouched()){
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            view.unproject(touchPoint);
            stateClick = true;
        } else {
            if (stateClick){
                stateClick = false;
                return touchPoint;
            }
        }
        return null;
    }

    public void setMainMenuScreen(){
        if (mainMenuScreen == null){
            mainMenuScreen = new MainMenuScreen(this);
            delScreens.add(mainMenuScreen);
        }
        this.setScreen(mainMenuScreen);
    }

    public void setGameScreen(boolean playRainMusic, boolean playDropSound){
        if(gameScreen == null){
            gameScreen = new GameScreen(this, playRainMusic, playDropSound);
            delScreens.add(gameScreen);
        }
        else{
            gameScreen.setPlayRainMusic(playRainMusic);
            gameScreen.setPlayDropSound(playDropSound);
        }
        this.setScreen(gameScreen);
    }

    public void setMusicSound(boolean playRainMusic, boolean playDropSound){
        gameScreen.setPlayRainMusic(playRainMusic);
        gameScreen.setPlayDropSound(playDropSound);
    }

    public void setGameScreen(){
        if(gameScreen == null){
            gameScreen = new GameScreen(this, true, true);
            delScreens.add(gameScreen);
        }
        this.setScreen(gameScreen);
    }

    public void setGameOverScreen(int[] array){
        if (gameOverScreen == null){
            gameOverScreen = new GameOverScreen(this);
            delScreens.add(gameOverScreen);
        }
        gameOverScreen.update(array);
        this.setScreen(gameOverScreen);
    }

    public void setHelpScreen(){
        if (helpScreen == null){
            helpScreen = new HelpScreen(this);
            delScreens.add(helpScreen);
        }
        this.setScreen(helpScreen);
    }

    public void setResultsScreen(){
        if (resultsScreen == null){
            resultsScreen = new ResultsScreen(this);
            delScreens.add(resultsScreen);
        }else {
            resultsScreen.update();
        }
        this.setScreen(resultsScreen);
    }

    public View view(){
        return view;
    }

    @Override
    public void dispose() {
        super.dispose();
        view.dispose();
        for (Screen current:delScreens) {
            current.dispose();
        }
        System.out.print("dispose\n");
    }
}
