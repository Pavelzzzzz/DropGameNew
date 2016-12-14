package com.mygdx.game.pavelzzzzz.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.game.pavelzzzzz.Drop;
import com.mygdx.game.pavelzzzzz.Score;
import com.mygdx.game.pavelzzzzz.realisation.GameScreen3;

/**
 * Created by Pavel on 30.11.16.
 */

public class GameOverScreen implements Screen {

    final Drop game;

    private int resultArray[];
    private Vector3 touchPoint;
    boolean stateClick;

    private com.mygdx.game.pavelzzzzz.buttons.Button buttonPlay;
    private com.mygdx.game.pavelzzzzz.buttons.Button buttonArrow;

    private BitmapFont font1;

    private OrthographicCamera camera;

    public GameOverScreen(final Drop gam, Screen previousScreen, int inputResultArray[]){
        game = gam;
        if (previousScreen != null) {
            previousScreen.dispose();
            System.out.print("dispose\n");
        }

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        final String FONT_CHARS = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        final String FONT_PATH = "OLDENGL.TTF";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 56;
        parameter.color = Color.GOLD;
        font1 = generator.generateFont(parameter);
        generator.dispose();

        resultArray = inputResultArray;
        buttonPlay = new com.mygdx.game.pavelzzzzz.buttons.Button("button/buttonPlayAgain.png",240, 350, 320, 100);
        buttonArrow = new com.mygdx.game.pavelzzzzz.buttons.Button("button/arrow.png", 10, 10, 64, 64);
        touchPoint = new Vector3();

        Score score = new Score();
        StringBuilder sb = new StringBuilder();
        sb.append("      ");
        sb.append(Integer.toString(inputResultArray[0]));
        sb.append("      ");
        sb.append(Integer.toString(inputResultArray[2]));
        sb.append("      ");
        sb.append(Integer.toString(inputResultArray[3]));
        sb.append("\n");
        score.setScore(new String[]{Integer.toString(inputResultArray[1]), sb.toString()});
        score.writeToFile();
        System.out.print("GameOverScreen created\n");
    }


    @Override
    public void show() {

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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        font1.draw(game.batch, "Collected drops: " + resultArray[0], 100, 300);
        font1.draw(game.batch, "Score: " + resultArray[1], 100, 250);
        font1.draw(game.batch, "Creation time: " + resultArray[2], 100, 200);
        font1.draw(game.batch, "Decline speed: " + resultArray[3], 100, 150);
        game.batch.draw(buttonPlay.getButtonImage(), buttonPlay.getArrayData()[0], buttonPlay.getArrayData()[1]);
        game.batch.draw(buttonArrow.getButtonImage(), buttonArrow.getArrayData()[0], buttonArrow.getArrayData()[1],
                buttonArrow.getArrayData()[2], buttonArrow.getArrayData()[3]);
        game.batch.end();

        if (handlerForClickingTheButton()) {
            if (buttonPlay.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new GameScreen3(game, this, true, true));
            }
            if (buttonArrow.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new MainMenuScreen2(game, this));
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
        font1.dispose();
        buttonPlay.dispose();
        buttonArrow.dispose();
    }


}