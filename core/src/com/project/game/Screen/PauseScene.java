package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.project.game.GameEngine;

public class PauseScene extends Scene{

    private Stage stage;

    public PauseScene(GameEngine gameEngine, SpriteBatch batch, BitmapFont font) {
        super(gameEngine, batch, font);
    }

    public void show() {
        stage = new Stage(new ScreenViewport());
        //hand input processor to stage class for main menu button clicks
        // see any implementation of io manager can do this
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gameEngine.ioManager);
        Gdx.input.setInputProcessor(multiplexer);

        Skin skin = new Skin(Gdx.files.internal("starsoldierui/star-soldier-ui.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Paused", skin);
        titleLabel.setFontScale(2); // Increase the font size (optional)
        table.add(titleLabel).padBottom(20); // Add some padding below the title
        table.row(); // Move to the next row

        TextButton ResumeButton = new TextButton("Resume", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        ResumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                // hand back control to iomanager for game control
                Gdx.input.setInputProcessor(gameEngine.ioManager);
                gameEngine.sceneManager.setCurrentScene("levelscene");
//                gameEngine.setScreen(new LevelScene(gameEngine, batch, font, gameEngine.sceneManager.getLevelScenePath()));
                gameEngine.simulationCycleManager.pauseGame();
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                Gdx.app.exit();
            }
        });

        table.add(ResumeButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exitButton).fillX().uniformX();
    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0.2f, 1);
//
//        camera.update();
//        batch.setProjectionMatrix(camera.combined);
//
//        // render all stuffs here as required
//        batch.begin();
//        font.draw(batch, "Pause Scene ", 100, 150);
//        batch.end();

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
