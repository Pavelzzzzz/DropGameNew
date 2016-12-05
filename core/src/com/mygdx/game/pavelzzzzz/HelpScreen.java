package com.mygdx.game.pavelzzzzz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Pavel on 05.12.16.
 */

public class HelpScreen  implements Screen {

    final Drop game;

    private Button buttonArrow;

    private Vector3 touchPoint;
    private boolean stateClick;

    private BitmapFont font1;
    private BitmapFont font2;

    OrthographicCamera camera;

    private Texture dropSimple;
    private Texture dropBasket;
    private Texture dropNumber;
    private Texture dropSpeed;
    private Texture dropPoison;

    public HelpScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        touchPoint = new Vector3();
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);

        dropSimple = new Texture("dropBlue.png");
        dropBasket = new Texture("dropTransparentGreen.png");
        dropNumber = new Texture("dropOrange.png");
        dropSpeed = new Texture("dropPurple.png");
        dropPoison = new Texture("dropLightGreenPoison.png");

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
        font2.draw(game.batch, "Help screen", 250, 475);
        font1.draw(game.batch, "Your main goal is to catch as many drop as you can.", 50, 400);
        font1.draw(game.batch, "Types of drops:", 80, 375);
        game.batch.draw(dropSimple, 110, 275);
        font1.draw(game.batch, " ->  Score +1", 180, 325);
        game.batch.draw(dropNumber, 110, 205);
        font1.draw(game.batch, " ->  Score +25; creation's speed /2", 180, 255);
        game.batch.draw(dropSpeed, 110, 135);
        font1.draw(game.batch, " ->  Score +25; decline speed *2", 180, 185);
        game.batch.draw(dropBasket, 110, 65);
        font1.draw(game.batch, " ->  Score +10; bucket's width *2", 180, 115);
        game.batch.draw(dropPoison, 110, 0);
        font1.draw(game.batch, " ->  Score +0; life -1", 180, 45);
        game.batch.draw(buttonArrow.getButtonImage(), buttonArrow.getArrayData()[0], buttonArrow.getArrayData()[1],
                buttonArrow.getArrayData()[2], buttonArrow.getArrayData()[3]);
        game.batch.end();

        if (handlerForClickingTheButton()) {
            if (buttonArrow.contains(touchPoint.x, touchPoint.y)){
                this.dispose();
                game.setScreen(new MainMenuScreen2(game));
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
