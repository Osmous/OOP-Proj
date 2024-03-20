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

    private Stage stage;
    private JsonValue levelData;
    private HealthBar healthBar;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private int enemyCount;
    private long nextSpawnTime;
    private JsonValue enemyData;
    private int spawnIntveral;

    public LevelScene(GameEngine gameEngine, SpriteBatch batch, BitmapFont font, String levelPath) {
        super(gameEngine, batch, font);
        // load level data from json file.
        JsonReader json = new JsonReader();
        this.levelData = json.parse(Gdx.files.internal(levelPath));
        this.spawnIntveral = this.levelData.getInt("spawnintervalmillis");
        nextSpawnTime=0;

        // call entityManager to create non enemy entity
        for (JsonValue entitydata : this.levelData.get("entities")) {
            if (!entitydata.getString("type").equals("enemy")) {
                // call entityManager to create non enemy entity
                this.gameEngine.entityManager.createEntity(entitydata);
            }else{
                enemyData = entitydata;
                enemyCount=entitydata.getInt("count");
            }
        }
        backgroundTexture = new Texture(Gdx.files.internal(this.levelData.getString("backgroundimage")));
        backgroundImage = new Image(backgroundTexture);

    }

    public void show() {
        stage = new Stage(new ScreenViewport());

        //set input processer for level to ioManager
        Gdx.input.setInputProcessor(gameEngine.ioManager);

        Skin skin = new Skin(Gdx.files.internal("starsoldierui/star-soldier-ui.json"));

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        for (Entity entity: this.gameEngine.entityManager.getLoadedEntity()){
            stage.addActor(entity);
        }

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("health", skin);
        table.add(titleLabel).fill().uniform();

        healthBar = new HealthBar(gameEngine.entityManager.getPlayerHealth());
        table.add(healthBar).fill().uniform().padLeft(5);
        table.top().left().padTop(5);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        healthBar.setHealth(gameEngine.entityManager.getPlayerHealth());
        gameEngine.entityManager.updateEnemyRotation();
        stage.draw();
        spawnEnemy();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Stage getStage() {
        return stage;
    }

    public void spawnEnemy(){
        if (enemyCount==0){
            return;
        }
        Random rand = new Random();
        int xG;
        int yG;
        int posX = 0;
        int posY = 0;

        if (System.currentTimeMillis()>nextSpawnTime){
            int spawncount = rand.nextInt(this.levelData.getInt("maxspawnlimitperinterval"))+1;
            if (spawncount>enemyCount){
                spawncount=enemyCount;
            }
            for (int i =0; i<spawncount; i++){
                while(true){
                    xG=rand.nextInt(3)+1;
                    yG=rand.nextInt(3)+1;
                    if(!(xG ==2 && yG==2)){
                        break;
                    }
                }

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
                JsonValue holder;
                holder = enemyData.get("posX");
                holder.set(String.valueOf(posX));
                holder = enemyData.get("posY");
                holder.set(String.valueOf(posY));
                this.gameEngine.entityManager.createEntity(enemyData);
            }
            for (Entity entity: this.gameEngine.entityManager.getLoadedEntity()) {
                stage.addActor(entity);
            }
                enemyCount= enemyCount-spawncount;
            nextSpawnTime = System.currentTimeMillis()+spawnIntveral;
        }
    }

    public int getEnemyCount() {
        return enemyCount;
    }
}