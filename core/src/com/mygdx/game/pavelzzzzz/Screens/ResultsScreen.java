package com.mygdx.game.pavelzzzzz.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.pavelzzzzz.*;


/**
 * Created by Pavel on 04.12.16.
 */

public class ResultsScreen implements Screen {

    final Drop game;

    private com.mygdx.game.pavelzzzzz.buttons.Button buttonArrow;

    private Vector3 touchPoint;
    private boolean stateClick;

    private String results;

    private BitmapFont font1;
    private BitmapFont font2;

    private OrthographicCamera camera;

    public ResultsScreen(Drop game, Screen previousScreen) {
        this.game = game;
        if (previousScreen != null) {
            previousScreen.dispose();
            System.out.print("dispose\n");
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        touchPoint = new Vector3();
        buttonArrow = new com.mygdx.game.pavelzzzzz.buttons.Button("button/arrow.png", 10, 10, 64, 64);
        Score score = new Score();
        results = score.getString();

        final String FONT_CHARS = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        final String FONT_PATH = "OLDENGL.TTF";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 32;
        parameter.color = Color.GOLD;
        font1 = generator.generateFont(parameter);
        parameter.size = 64;
        font2 = generator.generateFont(parameter);
        generator.dispose();
        System.out.print("ResultsScreen created\n");
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
        font2.draw(game.batch, "Results", 320, 460);
        //String s1 = String.format("%10s %10s %10s %10s", "Score", "Drop", "Creation time", "Speed");
        //font1.draw(game.batch, s1, 150, 380);
        font1.draw(game.batch, "Score  Drop  Speed  Creation time", 150, 380);
        font1.draw(game.batch, results, 150, 330);
        game.batch.draw(buttonArrow.getButtonImage(), buttonArrow.getArrayData()[0], buttonArrow.getArrayData()[1],
                        buttonArrow.getArrayData()[2], buttonArrow.getArrayData()[3]);
        game.batch.end();

        if (handlerForClickingTheButton()) {
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
        buttonArrow.dispose();
        font1.dispose();
        font2.dispose();
    }
}
