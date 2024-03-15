package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.GameEngine;

public class PauseScene extends Scene{

    public PauseScene(GameEngine gameEngine, SpriteBatch batch, BitmapFont font) {
        super(gameEngine, batch, font);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // render all stuffs here as required
        batch.begin();
        font.draw(batch, "Pause Scene ", 100, 150);
        batch.end();

        // this for changing screens
        // throw this if condition into IO manager
        // here temporarily. currently set to on mouse 1 down. need to change to buttons i guess. find someway to detect
        // only single button press (coz on button down is like holding m1 continuosly) maybe find someway to implement a
        // on button up stroke or smth idk
//        if (Gdx.input.isTouched()) {
//            gameEngine.sceneManager.setCurrentScene("levelscene");
//            gameEngine.setScreen(new LevelScene(gameEngine, batch, font, gameEngine.sceneManager.getLevelScenePath()));
//            dispose();
//        }
    }
}
