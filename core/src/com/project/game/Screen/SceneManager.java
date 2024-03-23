package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.game.Entity.Entity;
import com.project.game.GameEngine;
import com.project.game.Screen.MainMenuScene;

import java.util.logging.Level;

public class SceneManager {
    private GameEngine gameEngine;
    private String currentScene;
    private int levelNum;
    public SpriteBatch batch;

    public SceneManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        batch = new SpriteBatch();
        levelNum=0;
    }

    public void setMainScreen() {
        this.gameEngine.setScreen(new MainMenuScene(this.gameEngine, this.batch));
        currentScene = "mainmenuscene";
    }

    public void render() {
        // from com.badlogic.gdx.Game
        // just so i can put render here and not do a super.render() in game engine
        if (gameEngine.getScreen() != null) gameEngine.getScreen().render(Gdx.graphics.getDeltaTime());

        // check if level scene is active, then handle all ai control and next level check
        // enemy rotation update is done in aicontrolmanager.updateAI()
        if (currentScene.equals("levelscene")) {
            // entity rendering called under levelscene.java as stage.draw()
            // actual entity rendering code under Entity.java as draw() function
            nextLevelCheck();
            gameEngine.aiControlManager.updateAI();
        }


    }

    private void nextLevelCheck(){
        boolean flag = true;
        // check if player health is 0
        // if it is then end the game show lose screen
        if (this.gameEngine.entityManager.getPlayerHealth() <=0){
            currentScene = "endscene";
            levelNum=0;
            gameEngine.getScreen().dispose();
            gameEngine.setScreen(new LoseScene(gameEngine, batch));
            gameEngine.simulationCycleManager.endGame();
            return;
        }

        //check win conditions: enemy spawns remaining is 0 and no enemies currently loaded
        if (((LevelScene)gameEngine.getScreen()).getEnemyCount()!=0){
            flag = false;
        }else{
            for (Entity entity: gameEngine.entityManager.getLoadedEntity()){
                if (entity.getType().equals("enemy")){
                    flag = false;
                    break;
                }
            }
        }

        // if flag is true, meaning win conditoins met then either transition to next level or to win scene
        // win scene only if level is last level.
        if (flag){
            // check if level is last level
            if(levelNum == this.gameEngine.config.getInt("endLevel")){
                // transition to win scene
                currentScene = "endscene";
                levelNum=0;
                gameEngine.getScreen().dispose();
                gameEngine.setScreen(new EndScene(gameEngine, batch));
                gameEngine.simulationCycleManager.endGame();
                return;
            }
            // transition to next Level
            levelNum = levelNum +1;
            gameEngine.getScreen().dispose();
            gameEngine.setScreen(new LevelScene(gameEngine, batch, getLevelScenePath(String.valueOf(levelNum))));
        }
    }

    public void explosion(Vector2 pos){
        // generate explosion animation at given position
        // Explosion actor will self remove once animation is complete
        ExplosionActor explosion = new ExplosionActor(gameEngine.config.getString("explosionTexture"));
        explosion.setPosition(pos.x, pos.y);
        ((LevelScene)gameEngine.getScreen()).getStage().addActor(explosion);
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

    public Screen getScreen(){
        return gameEngine.getScreen();
    }
}