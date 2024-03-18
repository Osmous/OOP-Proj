package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.Entity.Entity;
import com.project.game.GameEngine;
import com.project.game.Screen.MainMenuScene;

import java.util.logging.Level;

public class SceneManager {
    private GameEngine gameEngine;
    private String currentScene;
    private int levelNum;
    public SpriteBatch batch;
    public BitmapFont font;

    public SceneManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        //set batch and font style
        batch = new SpriteBatch();
        font = new BitmapFont();
        levelNum=0;

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
//            gameEngine.entityManager.renderEntity(batch);
            // entity rendering called under levelscene.java as stage.draw()
            nextLevelCheck();
            gameEngine.entityManager.updateEnemyRotation();
            gameEngine.aiControlManager.updateAI();
            // test delete entity for level trasition check
//            if(levelNum==1){
//                gameEngine.entityManager.deleteEntity(1);
//                gameEngine.entityManager.deleteEntity(2);
//            }
//            System.out.println(((LevelScene)gameEngine.getScreen()).Testvar);
        }


    }

    private void nextLevelCheck(){
        boolean flag = true;
        // currently set to check if no enemy loaded
        // to do: add total number of enemy spawns left and if enemy is in loaded entity list
        // after redo spawning machanics
        for (Entity entity: gameEngine.entityManager.getLoadedEntity()){
            if (entity.getType().equals("enemy") || ((LevelScene)gameEngine.getScreen()).getEnemyCount()!=0){
                flag = false;
                break;
            }
        }
        if (flag){
            levelNum = levelNum +1;
            gameEngine.getScreen().dispose();
            gameEngine.setScreen(new LevelScene(gameEngine, batch, font, getLevelScenePath(String.valueOf(levelNum))));
        }
    }


    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
        if (currentScene.equals("levelscene") && gameEngine.simulationCycleManager.getCurrentState().equals("IDLE")){
            this.gameEngine.simulationCycleManager.startGame();
        }
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public String getLevelScenePath(String name){
//        if(currentScene.equals("levelScene")){
//            // this is a place holder for having multiple levels based on idk some condition. for now set to hard code level1.
//            // add more level.json files and add them to the config.json path
//            return this.gameEngine.config.get("levelPath").getString("level1");
//        }
        return this.gameEngine.config.get("levelPath").getString(name);
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        if (levelNum <=0){
            System.out.println("error levelNum <= 0");
            return;
        }
        this.levelNum = levelNum;
    }
}