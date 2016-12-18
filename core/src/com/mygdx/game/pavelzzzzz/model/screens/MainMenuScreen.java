package com.mygdx.game.pavelzzzzz.model.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.pavelzzzzz.ScreenManager;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;
import com.mygdx.game.pavelzzzzz.model.buttons.Button;
import com.mygdx.game.pavelzzzzz.model.buttons.ButtonWithTwoPosition;

/**
 * Created by Pavel on 17.12.16.
 */

public class MainMenuScreen implements Screen {

    private ScreenManager screenManager;

    private Button buttonPlay;
    private Button buttonResults;
    private Button buttonHelp;

    private Vector3 touchPoint;

    private DrawingString title;

    private boolean playRainMusic;
    private boolean playDropSound;

    private ButtonWithTwoPosition buttonSound;
    private ButtonWithTwoPosition buttonMusic;

    public MainMenuScreen (ScreenManager inputScreenManager) {

        screenManager = inputScreenManager;

        touchPoint = new Vector3();

        title = new DrawingString("Welcome to game menu!", 110, 460);

        buttonPlay = new Button("button/buttonPlay.png", 240, 280, 320, 100);
        buttonResults = new Button("button/buttonResults.png", 240, 180, 320, 100);
        buttonHelp = new Button("button/buttonHelp.png", 240, 80, 320, 100);
        buttonSound = new ButtonWithTwoPosition("button/buttonSoundOn.png", "button/buttonSoundOff.png", 10, 10, 64, 64);
        buttonMusic = new ButtonWithTwoPosition("button/buttonMusicOn.png", "button/buttonMusicOff.png", 10, 94, 64, 64);

        playRainMusic = true;
        playDropSound = true;

        System.out.print("MainMenuScreen created\n");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        screenManager.view().screenClear(0, 0, 0.5f, 1);

        screenManager.view().printStr64(title);
        screenManager.view().printImage(buttonPlay);
        screenManager.view().printImage(buttonResults);
        screenManager.view().printImage(buttonHelp);
        screenManager.view().printImage(buttonSound);
        screenManager.view().printImage(buttonMusic);

        touchPoint = screenManager.generationClickVector();

        if (touchPoint != null) {
            if (buttonPlay.contains(touchPoint.x, touchPoint.y)){
                screenManager.setGameScreen(playRainMusic, playDropSound);
            }

            if (buttonResults.contains(touchPoint.x, touchPoint.y)){
                screenManager.setResultsScreen();
            }

            if (buttonHelp.contains(touchPoint.x, touchPoint.y)){
                screenManager.setHelpScreen();
            }

            if (buttonSound.contains(touchPoint.x, touchPoint.y)){
                playDropSound = !playDropSound;
                buttonSound.setStatus(playDropSound);
                screenManager.setMusicSound(playRainMusic, playDropSound);
            }

            if (buttonMusic.contains(touchPoint.x, touchPoint.y)){
                playRainMusic = !playRainMusic;
                buttonMusic.setStatus(playRainMusic);
                screenManager.setMusicSound(playRainMusic, playDropSound);
            }
        }
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
        buttonPlay.dispose();
        buttonResults.dispose();
        buttonHelp.dispose();
        buttonMusic.dispose();
        buttonSound.dispose();
        System.out.print("MainMenuScreen disposed\n");
    }
}
