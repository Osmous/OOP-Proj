package com.project.game.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.GameEngine;

public class Scene implements com.badlogic.gdx.Screen {
    protected GameEngine gameEngine;
    protected OrthographicCamera camera;

    protected SpriteBatch batch;
    protected BitmapFont font;

    protected Scene(GameEngine gameEngine, SpriteBatch batch, BitmapFont font) {
        this.gameEngine = gameEngine;
        this.batch = batch;
        this.font = font;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}