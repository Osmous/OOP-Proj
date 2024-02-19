package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.GameEngine;
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
            gameEngine.entityManager.renderEntity(batch);
        }


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