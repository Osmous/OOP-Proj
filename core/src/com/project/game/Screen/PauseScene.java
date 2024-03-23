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

import static com.badlogic.gdx.utils.Align.topLeft;

public class PauseScene extends Scene{

    private Stage stage;

    public PauseScene(GameEngine gameEngine, SpriteBatch batch) {
        super(gameEngine, batch);
    }

    public void show() {
        // see EndScene for code comments. its all the same except the edits seen here
        stage = new Stage(new ScreenViewport());
        //set input processor to both Stage and Iomanager to allow for on screen button click event handling and
        //using keyboard inputs as a way to unpuase (ESC key)
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gameEngine.ioManager.getInputHandler());
        Gdx.input.setInputProcessor(multiplexer);

        Skin skin = new Skin(Gdx.files.internal(this.gameEngine.config.getString("skinPathJson")));

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

        Label titleLabel = new Label("Paused", skin);
        titleLabel.setFontScale(2);
        table.add(titleLabel).padBottom(20);
        table.row();

        TextButton ResumeButton = new TextButton("Resume", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        ResumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                // hand back control to iomanager for game control
                Gdx.input.setInputProcessor(gameEngine.ioManager.getInputHandler());
                gameEngine.sceneManager.setCurrentScene("levelscene");
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
