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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.project.game.GameEngine;

public class EndScene extends Scene{

    private Stage stage;

    public EndScene(GameEngine gameEngine, SpriteBatch batch) {
        super(gameEngine, batch);
    }

    public void show() {
        // new stage to handle actors, meaning table grid holding buttons and text
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // assets file
        Skin skin = new Skin(Gdx.files.local(this.gameEngine.config.getString("skinPathJson")));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton MuteButton = new TextButton("Mute BGM", skin);
        MuteButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                // hand back control to iomanager for game control
                gameEngine.ioManager.playBGM();
            }
        });
        table.add(MuteButton).pad(10);
        table.row();

        // screen title display
        Label titleLabel = new Label("YOU WIN", skin);
        titleLabel.setFontScale(2);
        table.add(titleLabel).padBottom(20);
        table.row();

        // init buttons
        TextButton mainMenuButton = new TextButton("Main menu", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // add event listener to button
        // check for any state change to the button
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                // hand back control to iomanager for game control
                Gdx.input.setInputProcessor(gameEngine.ioManager.getInputHandler());
                gameEngine.sceneManager.setMainScreen();
                gameEngine.simulationCycleManager.setCurrentStateIdle();
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                Gdx.app.exit();
            }
        });

        table.add(mainMenuButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exitButton).fillX().uniformX();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        stage.getViewport().update(width, height, true);
    }
}
