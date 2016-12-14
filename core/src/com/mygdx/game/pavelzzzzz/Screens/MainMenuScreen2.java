package com.mygdx.game.pavelzzzzz.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.pavelzzzzz.Drop;
import com.mygdx.game.pavelzzzzz.buttons.Button;
import com.mygdx.game.pavelzzzzz.realisation.GameScreen3;

/**
 * Created by Pavel on 29.11.16.
 */

public class MainMenuScreen2 implements Screen {

    final Drop game;

    private OrthographicCamera camera;

    private com.mygdx.game.pavelzzzzz.buttons.Button buttonPlay;
    private com.mygdx.game.pavelzzzzz.buttons.Button buttonResults;
    private com.mygdx.game.pavelzzzzz.buttons.Button buttonHelp;
    private Vector3 touchPoint;
    private boolean playRainMusic;
    private boolean playDropSound;
    private com.mygdx.game.pavelzzzzz.buttons.ButtonWithTwoPosition buttonSound;
    private com.mygdx.game.pavelzzzzz.buttons.ButtonWithTwoPosition buttonMusic;
    boolean stateClick;
    private BitmapFont font1;

    public MainMenuScreen2 (final Drop gam, Screen previousScreen){
        game = gam;
        if (previousScreen != null) {
            previousScreen.dispose();
            System.out.print("dispose\n");
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        buttonPlay = new com.mygdx.game.pavelzzzzz.buttons.Button("button/buttonPlay.png", 240, 280, 320, 100);
        buttonResults = new com.mygdx.game.pavelzzzzz.buttons.Button("button/buttonResults.png", 240, 180, 320, 100);
        buttonHelp = new Button("button/buttonHelp.png", 240, 80, 320, 100);
        buttonSound = new com.mygdx.game.pavelzzzzz.buttons.ButtonWithTwoPosition("button/buttonSoundOn.png", "button/buttonSoundOff.png", 10, 10, 64, 64);
        buttonMusic = new com.mygdx.game.pavelzzzzz.buttons.ButtonWithTwoPosition("button/buttonMusicOn.png", "button/buttonMusicOff.png", 10, 94, 64, 64);
        touchPoint = new Vector3();
        stateClick = false;
        playRainMusic = true;
        playDropSound = true;

        final String FONT_CHARS = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        final String FONT_PATH = "OLDENGL.TTF";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 64;
        parameter.color = Color.GOLD;
        font1 = generator.generateFont(parameter);
        generator.dispose();
        System.out.print("MainMenuScreen2 created\n");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        font1.draw(game.batch, "Welcome to game menu!", 110, 460);
        game.batch.draw(buttonPlay.getButtonImage(), buttonPlay.getArrayData()[0], buttonPlay.getArrayData()[1]);
        game.batch.draw(buttonResults.getButtonImage(), buttonResults.getArrayData()[0], buttonResults.getArrayData()[1]);
        game.batch.draw(buttonHelp.getButtonImage(), buttonHelp.getArrayData()[0], buttonHelp.getArrayData()[1]);
        game.batch.draw(buttonSound.getButtonImage(), buttonSound.getArrayData()[0], buttonSound.getArrayData()[1],
                        buttonSound.getArrayData()[2], buttonSound.getArrayData()[3]);
        game.batch.draw(buttonMusic.getButtonImage(), buttonMusic.getArrayData()[0], buttonMusic.getArrayData()[1],
                        buttonMusic.getArrayData()[2], buttonMusic.getArrayData()[3]);
        game.batch.end();

        if (handlerForClickingTheButton()) {
            if (buttonPlay.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new GameScreen3(game, this, playRainMusic, playDropSound));
            }

            if (buttonResults.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new ResultsScreen(game, this));
            }

            if (buttonHelp.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new HelpScreen(game, this));
            }

            if (buttonSound.contains(touchPoint.x, touchPoint.y)){
                playDropSound = !playDropSound;
                buttonSound.setStatus(playDropSound);
            }

            if (buttonMusic.contains(touchPoint.x, touchPoint.y)){
                playRainMusic = !playRainMusic;
                buttonMusic.setStatus(playRainMusic);
            }
        }
    }

    private boolean handlerForClickingTheButton(){
        if (Gdx.input.isTouched()){
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);
            stateClick = true;
        } else {
            if (stateClick){
                stateClick = false;
                return true;
            }
        }
        return false;
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
        font1.dispose();
    }
}