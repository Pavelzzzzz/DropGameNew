package com.mygdx.game.pavelzzzzz.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

import com.mygdx.game.pavelzzzzz.model.draw.DrawingObject;
import com.mygdx.game.pavelzzzzz.model.draw.DrawingString;

/**
 * Created by Pavel on 17.12.16.
 */

public class View {

    private OrthographicCamera camera;

    public SpriteBatch batch;

    private BitmapFont font1;
    private BitmapFont font2;

    public View(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();

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

    public void screenClear(float red, float green, float blue, float alpha){
        Gdx.gl.glClearColor(red, green, blue, alpha);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
    }

    public void unproject(Vector3 touchPoint){
        camera.unproject(touchPoint);
    }

    public void printImage(DrawingObject object){
        batch.begin();
        batch.draw(object.getImage(), object.getX(), object.getY(), object.getWidth(), object.getHeight());
        batch.end();
    }

    public void printStr32(DrawingString drawingString){
        batch.begin();
        font1.draw(batch, drawingString.getString(), drawingString.getX(), drawingString.getY());
        batch.end();
    }

    public void printStr64(DrawingString drawingString){
        batch.begin();
        font2.draw(batch, drawingString.getString(), drawingString.getX(), drawingString.getY());
        batch.end();
    }

    public void dispose(){
        batch.dispose();
        font1.dispose();
        font2.dispose();
    }
}
