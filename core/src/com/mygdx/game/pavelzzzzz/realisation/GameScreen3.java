package com.mygdx.game.pavelzzzzz.realisation;

/**
 * Created by Pavel on 26.11.16.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.pavelzzzzz.Button;
import com.mygdx.game.pavelzzzzz.ButtonWithTwoPosition;
import com.mygdx.game.pavelzzzzz.Drop;
import com.mygdx.game.pavelzzzzz.GameOverScreen;
import com.mygdx.game.pavelzzzzz.MainMenuScreen2;
import com.mygdx.game.pavelzzzzz.realisation.model.drop.DropPoison;
import com.mygdx.game.pavelzzzzz.realisation.model.Basket;
import com.mygdx.game.pavelzzzzz.realisation.model.DropsAction;

import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen3 implements Screen {

    final Drop game;
    private OrthographicCamera camera;

    private Vector3 touchPoint;
    boolean stateClick;

    private Button buttonArrow;
    private ButtonWithTwoPosition buttonPause;
    private ButtonWithTwoPosition buttonSound;
    private ButtonWithTwoPosition buttonMusic;

    private Sound dropSound;
    private Music rainMusic;
    private Vector3 touchPos;

    private long lastDropTime;
    private long dropsGathered;
    private long dropsScore;
    private int life;

    private Basket basket;
    private DropsAction dropsAction;
    private com.mygdx.game.pavelzzzzz.realisation.model.DropArray dropArray;
    private com.mygdx.game.pavelzzzzz.realisation.model.DropCreator creator;
    private DropDeclining dropDeclining;
    private DropsCreateSpeed dropsCreateSpeed;

    private boolean playDropSound;
    private boolean playRainMusic;
    private boolean pauseOn;

    private BitmapFont font1;

    public GameScreen3 (final Drop gam, boolean inputPlayRainMusic, boolean inputPlayDropSound) {
        this.game = gam;
        life = 5;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        touchPoint = new Vector3();
        stateClick = false;
        buttonPause = new ButtonWithTwoPosition("button/buttonPauseOn.png", "button/buttonPauseOff.png", 726, 406, 64, 64);
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);
        buttonSound = new ButtonWithTwoPosition("button/buttonSoundOn.png", "button/buttonSoundOff.png", 10, 94, 64, 64);
        buttonSound.setStatus(inputPlayDropSound);
        buttonMusic = new ButtonWithTwoPosition("button/buttonMusicOn.png", "button/buttonMusicOff.png", 10, 178, 64, 64);
        buttonMusic.setStatus(inputPlayRainMusic);

        pauseOn = false;
        touchPos = new Vector3();
        playDropSound = inputPlayDropSound;

        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("undertreeinrain.mp3"));
        rainMusic.setLooping(true);

        playRainMusic = inputPlayRainMusic;
        if (inputPlayRainMusic) {
            rainMusic.play();
        }

        basket = new Basket();

        dropArray = new com.mygdx.game.pavelzzzzz.realisation.model.DropArray();
        dropDeclining = new DropDeclining();
        dropsCreateSpeed = new DropsCreateSpeed();
        dropsAction = new DropsAction(this, basket, dropDeclining, dropsCreateSpeed);

        creator = new com.mygdx.game.pavelzzzzz.realisation.model.DropCreator(dropsAction);

        final String FONT_CHARS = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        final String FONT_PATH = "OLDENGL.TTF";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 24;
        parameter.color = Color.GOLD;
        font1 = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() {
        //rainMusic.play();
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

    public void lifeSubtraction(){
        life--;
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        for (int i = 0; i < dropArray.size(); i++){
            game.batch.draw(dropArray.getType(i).getImage(),
                    dropArray.getRectangle(i).x,
                    dropArray.getRectangle(i).y);
        }
        font1.draw(game.batch, "Drops Collected: " + dropsGathered, 10, 470);
        font1.draw(game.batch, "Score: " + dropsScore, 10, 440);
        font1.draw(game.batch, "Create time: " + dropsCreateSpeed.getCreateSpeedTime()/100000, 10, 410);
        font1.draw(game.batch, "Speed: " + dropDeclining.getDropSpeed(), 10, 380);
        font1.draw(game.batch, "Life: " + life, 10, 350);
        game.batch.draw(basket.getImage(), basket.getRectangleX(), basket.getRectangleY(), basket.rectangleWidth(), basket.rectangleHeight());
        game.batch.draw(buttonPause.getButtonImage(), buttonPause.getArrayData()[0], buttonPause.getArrayData()[1],
                buttonPause.getArrayData()[2], buttonPause.getArrayData()[3]);
        game.batch.end();
        dropDeclining.update();
        basket.update();
        dropsCreateSpeed.update();

        if (handlerForClickingTheButton()) {
            if (buttonPause.contains(touchPoint.x, touchPoint.y)) {
                this.pause();
            }
            if (pauseOn){
                if (buttonSound.contains(touchPoint.x, touchPoint.y)){
                    playDropSound = !playDropSound;
                    buttonSound.setStatus(playDropSound);
                }

                if (buttonMusic.contains(touchPoint.x, touchPoint.y)){
                    playRainMusic = !playRainMusic;
                    buttonMusic.setStatus(playRainMusic);
                    if (playRainMusic){
                        rainMusic.play();
                    }else{
                        rainMusic.pause();
                    }
                }
                if (buttonArrow.contains(touchPoint.x, touchPoint.y)){
                    this.dispose();
                    game.setScreen(new MainMenuScreen2(game));
                }
            }
        }
        if (pauseOn ==false){
            if (Gdx.input.isTouched()){
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                basket.setRectangleX((int) (touchPos.x - basket.rectangleWidth() / 2));
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                basket.setRectangleX(basket.getRectangleX() - (300* Gdx.graphics.getDeltaTime()));
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                basket.setRectangleX(basket.getRectangleX() + (300* Gdx.graphics.getDeltaTime()));

            if (basket.getRectangleX() < 0)
                basket.setRectangleX(0);
            if (basket.getRectangleX() > 800 - basket.rectangleWidth())
                basket.setRectangleX(800 - basket.rectangleWidth());

            if (TimeUtils.nanoTime() - lastDropTime > dropsCreateSpeed.getCreateSpeedTime()){
                lastDropTime = TimeUtils.nanoTime();
                creator.createDrop(dropArray);
            }

            ArrayList<Integer> delArray = new ArrayList<Integer>();

            for (int i = 0; i < dropArray.size(); i++){
                dropArray.setY(i, (dropArray.getRectangle(i).y - dropDeclining.getDropSpeed() * Gdx.graphics.getDeltaTime()));
                if (dropArray.getRectangle(i).y + 64 < 0){
                    if (!(dropArray.getType(i) instanceof DropPoison)){
                        life--;
                    }
                    if (life < 1){
                        this.dispose();
                        long speed =new Long(dropsCreateSpeed.getCreateSpeedTime()/100000);
                        game.setScreen(new GameOverScreen(game, new int []{(int)dropsGathered, (int)dropsScore, (int)speed,  dropDeclining.getDropSpeed()}));
                    }
                    delArray.add(i);
                }
                if (basket.overlaps(dropArray.getRectangle(i))){
                    dropsGathered++;
                    dropsScore += dropArray.getType(i).getScore();
                    if (playDropSound){
                        dropSound.play();
                    }
                    dropArray.getType(i).action();
                    delArray.add(i);
                }
            }
            Iterator<Integer> iter = delArray.iterator();
            while (iter.hasNext()){
                dropArray.remove(iter.next());
            }
        }else{
            game.batch.begin();
            game.batch.draw(buttonArrow.getButtonImage(), buttonArrow.getArrayData()[0], buttonArrow.getArrayData()[1],
                    buttonArrow.getArrayData()[2], buttonArrow.getArrayData()[3]);
            game.batch.draw(buttonSound.getButtonImage(), buttonSound.getArrayData()[0], buttonSound.getArrayData()[1],
                    buttonSound.getArrayData()[2], buttonSound.getArrayData()[3]);
            game.batch.draw(buttonMusic.getButtonImage(), buttonMusic.getArrayData()[0], buttonMusic.getArrayData()[1],
                    buttonMusic.getArrayData()[2], buttonMusic.getArrayData()[3]);
            game.batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        pauseOn = !pauseOn;
        buttonPause.setStatus(!pauseOn);
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        dropSound.dispose();
        rainMusic.dispose();
        basket.dispose();
        buttonPause.dispose();
        buttonMusic.dispose();
        buttonSound.dispose();
        buttonArrow.dispose();
        creator.dispose();
        font1.dispose();
    }
}