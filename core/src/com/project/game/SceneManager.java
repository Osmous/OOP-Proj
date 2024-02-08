package com.project.game;
import com.badlogic.gdx.Gdx;
import com.project.game.Screen.MainMenuScreen;

public class SceneManager {
    private GameEngine gameEngine;
    public SceneManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

    }
    public void setMainScreen(){
        this.gameEngine.setScreen(new MainMenuScreen(this.gameEngine));
    }

    public void render() {
        // from com.badlogic.gdx.Game
        // just so i can put render here and not do a super.render() in game engine
        if (gameEngine.getScreen() != null) gameEngine.getScreen().render(Gdx.graphics.getDeltaTime());
    }

}
