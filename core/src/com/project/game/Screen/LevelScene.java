package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.GameEngine;

public class LevelScene extends Screen{

    public LevelScene(GameEngine gameEngine, SpriteBatch batch, BitmapFont font) {
        super(gameEngine, batch, font);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // render all stuffs here as required
        // reference to gameEngine.entityManager.getEntites() shd be called here
        batch.begin();
        font.draw(batch, "This is the 2nd Screen", 200, 400);
        batch.end();

        // this for changing screens
        // throw this if condition into IO manager
        // here temporarily. currently set to on mouse 1 down. need to change to buttons i guess. find someway to detect
        // only single button press (coz on button down is like holding m1 continuosly) maybe find someway to implement a
        // on button up stroke or smth idk
        if (Gdx.input.isTouched()) {
            gameEngine.setScreen(new MainMenuScreen(gameEngine,batch,font));
            dispose();
        }
    }
}
