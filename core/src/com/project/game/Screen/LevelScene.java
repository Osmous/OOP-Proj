package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.project.game.Entity.EnemyEntity;
import com.project.game.Entity.Entity;
import com.project.game.Entity.PlayerEntity;
import com.project.game.GameEngine;
import com.project.game.Screen.HUD.HealthBar;

import java.util.Random;

public class LevelScene extends Scene {

    protected Stage stage;
    private JsonValue levelData;
    private HealthBar healthBar;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private int enemyCount;
    private long nextSpawnTime;
    private JsonValue enemyData;
    private int spawnIntveral;

    public LevelScene(GameEngine gameEngine, SpriteBatch batch, String levelPath) {
        super(gameEngine, batch);
        // load level data from json file.
        JsonReader json = new JsonReader();
        this.levelData = json.parse(Gdx.files.local(levelPath));
        this.spawnIntveral = this.levelData.getInt("spawnintervalmillis");
        nextSpawnTime=0;

        backgroundTexture = new Texture(Gdx.files.local(this.levelData.getString("backgroundimage")));
        backgroundImage = new Image(backgroundTexture);

    }

    public void show() {
        stage = new Stage(new ScreenViewport());

        //set input processer for level to ioManager
        Gdx.input.setInputProcessor(gameEngine.ioManager.getInputHandler());

        Skin skin = new Skin(Gdx.files.local(this.gameEngine.config.getString("skinPathJson")));

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        // if statement to check if show() is called after resuming game from pause state
        // see pauseGame and performResumeGame in Simulation lifecycle.
        // this works because this show() function is called in setScreen which is called before simulationlifecycle
        // changes the gameState enum variable.
        if (!this.gameEngine.simulationCycleManager.getCurrentState().equals("PAUSE")) {
            for(Entity entity : this.gameEngine.entityManager.getLoadedEntity()){
                stage.addActor(entity);
            }
            // call entityManager to create non enemy entity
            for (JsonValue entitydata : this.levelData.get("entities")) {
                if (!entitydata.getString("type").equals("enemy")) {
                    // call entityManager to create non enemy entity
                    this.gameEngine.entityManager.createEntity(entitydata);
                } else {
                    enemyData = entitydata;
                    enemyCount = entitydata.getInt("count");
                }
            }
        }
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("health", skin);
        table.add(titleLabel).fill().uniform();

        // add healthbar overlay
        healthBar = new HealthBar(gameEngine.entityManager.getPlayerHealth());
        table.add(healthBar).fill().uniform().padLeft(5);
        table.top().left().padTop(5);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        healthBar.setHealth(gameEngine.entityManager.getPlayerHealth());
        gameEngine.entityManager.updateEnemyRotation();
        stage.act();
        stage.draw();
        spawnEnemy();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        stage.getViewport().update(width, height, true);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Stage getStage() {
        return stage;
    }
    public LevelScene setStage(Stage stage){
        this.stage = stage;
        return this;
    }

    public void spawnEnemy(){
        // spawn mechanics
        // if enemy left to spawn count is 0 then return
        if (enemyCount==0){
            return;
        }
        // initalise random
        // spawning mechanics based on a 3x3 grid
        //  1,3 | 2,3 | 3,3
        //  1,2 | 2,2 | 3,2
        //  1,1 | 2,1 | 3,1
        //  2,2 is the visable screen, so this is marked as not spawnable so enemies will only spawn off screen and move
        //  into the screen. xG variable for the column number(first number), yG variable for the row number(2nd number)
        Random rand = new Random();
        int xG;
        int yG;
        int posX = 0;
        int posY = 0;
        // check if it is time to spawn enemy
        if (System.currentTimeMillis()>nextSpawnTime){
            // determine spawn number for this iteration
            int spawncount = rand.nextInt(this.levelData.getInt("maxspawnlimitperinterval"))+1;
            // if spawn count is more than enemy remaining spawns then set spawn to enemy remaining spawn count
            if (spawncount>enemyCount){
                spawncount=enemyCount;
            }
            for (int i =0; i<spawncount; i++){
                while(true){
                    xG=rand.nextInt(3)+1;
                    yG=rand.nextInt(3)+1;
                    // if group 2,2 is selected (visable screen) then reroll the spawn group
                    if(!(xG ==2 && yG==2)){
                        break;
                    }
                }
                // based on which group selected calculate x spawn position
                switch (xG){
                    case(1):
                        posX = -rand.nextInt((int)stage.getWidth()/5);
                        break;
                    case(2):
                        posX = rand.nextInt((int)stage.getWidth());
                        break;
                    case(3):
                        posX =(int)(stage.getWidth())+rand.nextInt((int)stage.getWidth()/5);
                        break;
                }
                // based on which group selected calculate y spawn position
                switch (yG){
                    case(1):
                        posY = -rand.nextInt((int)stage.getHeight()/5);
                        break;
                    case(2):
                        posY = rand.nextInt((int)stage.getHeight());
                        break;
                    case(3):
                        posY =(int)(stage.getHeight())+rand.nextInt((int)stage.getHeight()/5);
                        break;
                }
                // edit enemy data spawn position
                JsonValue holder;
                holder = enemyData.get("posX");
                holder.set(String.valueOf(posX));
                holder = enemyData.get("posY");
                holder.set(String.valueOf(posY));
                // create entity at spawn position
                this.gameEngine.entityManager.createEntity(enemyData);
            }
            // update enemy spawns remaining
            enemyCount= enemyCount-spawncount;
            // update next spawn time
            nextSpawnTime = System.currentTimeMillis()+spawnIntveral;
        }
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    @Override
    public void dispose(){
        super.dispose();
        healthBar.dispose();
    }

}