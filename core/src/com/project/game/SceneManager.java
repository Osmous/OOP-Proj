package com.project.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.Screen.MainMenuScreen;

public class SceneManager {
    private GameEngine gameEngine;
    public SpriteBatch batch;
    public BitmapFont font;

    public SceneManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        //set batch and font style
        batch = new SpriteBatch();
        font = new BitmapFont();

    }
    public void setMainScreen(){
        this.gameEngine.setScreen(new MainMenuScreen(this.gameEngine,this.batch,this.font));
    }

    public void render() {
        // from com.badlogic.gdx.Game
        // just so i can put render here and not do a super.render() in game engine
        if (gameEngine.getScreen() != null) gameEngine.getScreen().render(Gdx.graphics.getDeltaTime());
    }

}
