package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.Entity.Entity;
import com.project.game.Entity.iDrawEntity;
import com.project.game.Screen.MainMenuScene;

public class SceneManager {
    private GameEngine gameEngine;
    private String currentScene;
    public SpriteBatch batch;
    public BitmapFont font;

    public SceneManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        //set batch and font style
        batch = new SpriteBatch();
        font = new BitmapFont();

    }

    public void setMainScreen() {
        this.gameEngine.setScreen(new MainMenuScene(this.gameEngine, this.batch, this.font));
        currentScene = "mainmenuscene";
    }

    public void render() {
        // from com.badlogic.gdx.Game
        // just so i can put render here and not do a super.render() in game engine
        if (gameEngine.getScreen() != null) gameEngine.getScreen().render(Gdx.graphics.getDeltaTime());
        // check if level scene is active, then render all loaded entites in entity manager
        if (currentScene.equals("levelscene")) {
            for (Entity entity : gameEngine.entityManager.getLoadedEntity()) {
                renderEntity(entity);

                //this shd be placed into io manager/ control managers but here just for now
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && entity.getType().equals("player"))
                    entity.setPosX((int) (entity.getPosX() - 200 * Gdx.graphics.getDeltaTime()));
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && entity.getType().equals("player"))
                    entity.setPosX((int) (entity.getPosX() + 200 * Gdx.graphics.getDeltaTime()));

                if (Gdx.input.isKeyPressed(Input.Keys.UP) && entity.getType().equals("enemy"))
                    entity.setPosY((int) (entity.getPosY() + 200 * Gdx.graphics.getDeltaTime()));
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && entity.getType().equals("enemy"))
                    entity.setPosY((int) (entity.getPosY() - 200 * Gdx.graphics.getDeltaTime()));
            }
        }


    }

    public void renderEntity(iDrawEntity draw) {
        //call renderEntity in each entity in loadedEntities using interface iDrawEntity
        // for assignment requirements
        draw.renderEntity(batch);
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }

    public String getLevelScenePath(){
//        if(currentScene.equals("levelScene")){
//            // this is a place holder for having multiple levels based on idk some condition. for now set to hard code level1.
//            // add more level.json files and add them to the config.json path
//            return this.gameEngine.config.get("levelPath").getString("level1");
//        }
        return this.gameEngine.config.get("levelPath").getString("level1");
    }
}
