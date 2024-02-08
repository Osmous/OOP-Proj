package com.project.game.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.project.game.GameEngine;

public class Screen implements com.badlogic.gdx.Screen {
    protected GameEngine gameEngine;
    protected OrthographicCamera camera;
    public Screen(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

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
