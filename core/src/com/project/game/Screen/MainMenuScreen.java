package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.GameEngine;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        gameEngine.batch.setProjectionMatrix(camera.combined);

        // render all stuffs here as required
        gameEngine.batch.begin();
        gameEngine.font.draw(gameEngine.batch, "Screen 1 ", 100, 150);
        gameEngine.batch.end();

        // this for changing screens
        // throw this if condition into IO manager
        // here temporarily. currently set to on mouse 1 down. need to change to buttons i guess. find someway to detect
        // only single button press (coz on button down is like holding m1 continuosly) maybe find someway to implement a
        // on button up stroke or smth idk
        if (Gdx.input.isTouched()) {
            gameEngine.setScreen(new LevelScene(gameEngine));
            dispose();
        }
    }
}
