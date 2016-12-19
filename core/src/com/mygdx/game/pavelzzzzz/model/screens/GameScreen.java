package com.mygdx.game.pavelzzzzz.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.pavelzzzzz.ScreenManager;
import com.mygdx.game.pavelzzzzz.model.buttons.Button;
import com.mygdx.game.pavelzzzzz.model.buttons.ButtonWithTwoPosition;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingImage;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;
import com.mygdx.game.pavelzzzzz.model.items.DropDeclining;
import com.mygdx.game.pavelzzzzz.model.items.DropsCreateSpeed;
import com.mygdx.game.pavelzzzzz.model.items.model.Basket;
import com.mygdx.game.pavelzzzzz.model.items.model.DropArray;
import com.mygdx.game.pavelzzzzz.model.items.model.DropCreator;
import com.mygdx.game.pavelzzzzz.model.items.model.DropsAction;
import com.mygdx.game.pavelzzzzz.model.items.model.drop.DropPoison;

/**
 * Created by Pavel on 17.12.16.
 */

public class GameScreen implements Screen {

    private ScreenManager screenManager;

    private Vector3 touchPoint;

    private Button buttonArrow;
    private ButtonWithTwoPosition buttonPause;
    private ButtonWithTwoPosition buttonSound;
    private ButtonWithTwoPosition buttonMusic;

    private Sound dropSound;
    private Music rainMusic;

    private long lastDropTime;
    private long dropsGathered;
    private long dropsScore;
    private int life;

    private Basket basket;
    private DropsAction dropsAction;
    private DropArray dropArray;
    private DropCreator creator;
    private DropDeclining dropDeclining;
    private DropsCreateSpeed dropsCreateSpeed;

    private DrawingImage buffer;

    private boolean playDropSound;
    private boolean playRainMusic;
    private boolean pauseOn;

    public GameScreen (ScreenManager inputScreenManager, boolean inputPlayRainMusic, boolean inputPlayDropSound) {

        screenManager = inputScreenManager;

        life = 5;

        touchPoint = new Vector3();
        buttonPause = new ButtonWithTwoPosition("button/buttonPauseOn.png", "button/buttonPauseOff.png", 726, 406, 64, 64);
        buttonArrow = new Button("button/arrow.png", 10, 10, 64, 64);
        buttonSound = new ButtonWithTwoPosition("button/buttonSoundOn.png", "button/buttonSoundOff.png", 10, 94, 64, 64);
        buttonSound.setStatus(inputPlayDropSound);
        buttonMusic = new ButtonWithTwoPosition("button/buttonMusicOn.png", "button/buttonMusicOff.png", 10, 178, 64, 64);
        buttonMusic.setStatus(inputPlayRainMusic);

        pauseOn = false;
        playDropSound = inputPlayDropSound;

        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("undertreeinrain.mp3"));
        rainMusic.setLooping(true);

        playRainMusic = inputPlayRainMusic;
        if (inputPlayRainMusic) {
            rainMusic.play();
        }

        basket = new Basket();

        dropArray = new DropArray();
        dropDeclining = new DropDeclining();
        dropsCreateSpeed = new DropsCreateSpeed();
        dropsAction = new DropsAction(this, basket, dropDeclining, dropsCreateSpeed);

        creator = new DropCreator(dropsAction);

        System.out.print("GameScreen created\n");
    }

    @Override
    public void show() {
        //rainMusic.play();
    }

    public void setPlayRainMusic(boolean inputPlayRainMusic){
        playRainMusic = inputPlayRainMusic;
        buttonMusic.setStatus(inputPlayRainMusic);
    }

    public void setPlayDropSound(boolean inputPlayDropSound){
        playDropSound = inputPlayDropSound;
        buttonSound.setStatus(inputPlayDropSound);
    }

    private void refreshGame(){
        life = 5;
        dropDeclining = new DropDeclining();
        dropsCreateSpeed = new DropsCreateSpeed();
        dropArray = new DropArray();
        dropsScore = 0;
        dropsGathered = 0;
    }

    public void lifeSubtraction(){
        life--;
    }

    @Override
    public void render (float delta) {
        screenManager.view().screenClear(0, 0, 0.2f, 1);

        for (int i = 0; i < dropArray.size(); i++){
            buffer = new DrawingImage(dropArray.getType(i).getImage(),
                    dropArray.getRectangle(i).x,
                    dropArray.getRectangle(i).y, 64, 64);
            screenManager.view().printImage(buffer);
        }
        screenManager.view().printStr32(new DrawingString("Collected drops: " + dropsGathered, 10, 470));
        screenManager.view().printStr32(new DrawingString("Score: " + dropsScore, 10, 440));
        screenManager.view().printStr32(new DrawingString("Creation time: " + dropsCreateSpeed.getCreateSpeedTime()/100000, 10, 410));
        screenManager.view().printStr32(new DrawingString("Decline speed: " + dropDeclining.getDropSpeed(), 10, 380));
        screenManager.view().printStr32(new DrawingString("Life: " + life, 10, 350));
        screenManager.view().printImage(basket);
        screenManager.view().printImage(buttonPause);

        dropDeclining.update();
        basket.update();
        dropsCreateSpeed.update();

        touchPoint = screenManager.generationClickVector();
        if (touchPoint != null) {
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
                    rainMusic.pause();
                    playRainMusic = false;
                    buttonMusic.setStatus(playRainMusic);
                    screenManager.setMainMenuScreen();
                }
            }
        }
        if (pauseOn == false){
            if (Gdx.input.isTouched()){
                touchPoint = new Vector3();
                touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                screenManager.view().unproject(touchPoint);
                basket.setRectangleX((int) (touchPoint.x - basket.getWidth() / 2));
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                basket.setRectangleX(basket.getX() - (300* Gdx.graphics.getDeltaTime()));
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                basket.setRectangleX(basket.getX() + (300* Gdx.graphics.getDeltaTime()));

            if (basket.getX() < 0)
                basket.setRectangleX(0);
            if (basket.getX() > 800 - basket.getWidth())
                basket.setRectangleX(800 - basket.getWidth());

            if (TimeUtils.nanoTime() - lastDropTime > dropsCreateSpeed.getCreateSpeedTime()){
                lastDropTime = TimeUtils.nanoTime();
                creator.createDrop(dropArray);
            }

            Array<Integer> delArray = new Array<Integer>();

            for (int i = 0; i < dropArray.size(); i++){
                dropArray.setY(i, (dropArray.getRectangle(i).y - dropDeclining.getDropSpeed() * Gdx.graphics.getDeltaTime()));
                if (dropArray.getRectangle(i).y + 64 < 0){
                    if (!(dropArray.getType(i) instanceof DropPoison)){
                        life--;
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
                if (life < 0){
                    rainMusic.pause();
                    playRainMusic = false;
                    buttonMusic.setStatus(playRainMusic);
                    long speed =new Long(dropsCreateSpeed.getCreateSpeedTime()/100000);
                    screenManager.setGameOverScreen(new int []{(int)dropsGathered, (int)dropsScore, (int)speed,  dropDeclining.getDropSpeed()});
                    refreshGame();
                    return;
                }
            }

            for (int i: delArray){
                dropArray.remove(i);
            }
        }else{
            screenManager.view().printImage(buttonArrow);
            screenManager.view().printImage(buttonSound);
            screenManager.view().printImage(buttonMusic);
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
        System.out.print("GameScreen disposed\n");
    }
}
