package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.project.game.Entity.Entity;
import com.project.game.GameEngine;
import com.project.game.Screen.HUD.HealthBar;

public class LevelScene extends Scene {

    private Stage stage;
    private JsonValue levelData;
    private HealthBar healthBar;
    private Texture backgroundTexture;
    private Image backgroundImage;

    public LevelScene(GameEngine gameEngine, SpriteBatch batch, BitmapFont font, String levelPath) {
        super(gameEngine, batch, font);
        // load level data from json file.
        JsonReader json = new JsonReader();
        this.levelData = json.parse(Gdx.files.internal(levelPath));

        // call entityManager to create Entity
        for (JsonValue entitydata : this.levelData.get("entities")) {
            this.gameEngine.entityManager.createEntity(entitydata);
        }
        backgroundTexture = new Texture(Gdx.files.internal(this.levelData.getString("backgroundimage")));
        backgroundImage = new Image(backgroundTexture);
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

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        for (Entity entity: this.gameEngine.entityManager.getLoadedEntity()){
            stage.addActor(entity);
        }

        // Initialize the health bar with the stage and max health
//        healthBar = new HealthBar(stage, gameEngine.entityManager.getPlayerHealth()); // For example, max health is 5
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("health", skin);
        table.add(titleLabel).fill().uniform();

        healthBar = new HealthBar(gameEngine.entityManager.getPlayerHealth());
//        healthBar.setPosition(20, Gdx.graphics.getHeight() - 50);
        table.add(healthBar).fill().uniform().padLeft(5);
        table.top().left().padTop(5);
//        stage.addActor(healthBar);
    }

    @Override
    public void render(float delta) {
//        // set screen colour, maybe set screen proper background idk. placeholder
//        ScreenUtils.clear(
//                this.levelData.get("backgroundcolour").getFloat(0) / 255,
//                this.levelData.get("backgroundcolour").getFloat(1) / 255,
//                this.levelData.get("backgroundcolour").getFloat(2) / 255,
//                this.levelData.get("backgroundcolour").getFloat(3) / 255);
//
//        camera.update();
//        batch.setProjectionMatrix(camera.combined);
//
//        // render screen code here. entity render done seperately in SceneManager
//        batch.begin();
//        font.draw(batch, "This is the 2nd Screen", 200, 400);
//        batch.end();
//
//        // this for changing screens
//        // throw this if condition into IO manager
//        // here temporarily. currently set to on mouse 1 down. need to change to buttons i guess. find someway to detect
//        // only single button press (coz on button down is like holding m1 continuosly) maybe find someway to implement a
//        // on button up stroke or smth idk
//        //
//        //************************ IMPORTANT WHEN SWITCHING LEVEL SCENES REMEMER TO CLEAR ENTITYMANAGER ENITIY ARRAY ******************************
////        if (Gdx.input.isTouched()) {
////            gameEngine.setScreen(new MainMenuScreen(gameEngine,batch,font));
////            gameEngine.sceneManager.setCurrentScene("mainmenuscene");
////            dispose();
////            gameEngine.entityManager.clearAllEntities();
////        }
//        Gdx.gl.glClearColor(
//                this.levelData.get("backgroundcolour").getFloat(0) / 255,
//                this.levelData.get("backgroundcolour").getFloat(1) / 255,
//                this.levelData.get("backgroundcolour").getFloat(2) / 255,
//                this.levelData.get("backgroundcolour").getFloat(3) / 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the health bar as needed, e.g., healthBar.updateHealth(newHealthValue);

//        healthBar.updateHealth(gameEngine.entityManager.getPlayerHealth());
        healthBar.setHealth(gameEngine.entityManager.getPlayerHealth());
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Stage getStage() {
        return stage;
    }

}