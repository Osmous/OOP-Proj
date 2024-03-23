package com.project.game.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.GameEngine;

public class Scene implements com.badlogic.gdx.Screen {
    protected GameEngine gameEngine;
    protected OrthographicCamera camera;

    protected SpriteBatch batch;

    protected Scene(GameEngine gameEngine, SpriteBatch batch) {
        this.gameEngine = gameEngine;
        this.batch = batch;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight=height;
        camera.viewportWidth=width;
        camera.position.set(camera.zoom * camera.viewportWidth / 2.0F, camera.zoom * camera.viewportHeight / 2.0F, 0.0F);
        camera.update();
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

    public OrthographicCamera getCamera() {
        return camera;
    }
}