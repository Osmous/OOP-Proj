package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.GameEngine;

public class LevelScene extends Scene {

    private JsonValue levelData;

    public LevelScene(GameEngine gameEngine, SpriteBatch batch, BitmapFont font, String levelPath) {
        super(gameEngine, batch, font);
        // load level data from json file.
        JsonReader json = new JsonReader();
        this.levelData = json.parse(Gdx.files.internal(levelPath));

        // call entityManager to create Entity
        for (JsonValue entitydata : this.levelData.get("entities")) {
            this.gameEngine.entityManager.createEntity(entitydata);
        }
    }

    @Override
    public void render(float delta) {
        // set screen colour, maybe set screen proper background idk. placeholder
        ScreenUtils.clear(
                this.levelData.get("backgroundcolour").getFloat(0) / 255,
                this.levelData.get("backgroundcolour").getFloat(1) / 255,
                this.levelData.get("backgroundcolour").getFloat(2) / 255,
                this.levelData.get("backgroundcolour").getFloat(3) / 255);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // render screen code here. entity render done seperately in SceneManager
        batch.begin();
        font.draw(batch, "This is the 2nd Screen", 200, 400);
        batch.end();

        // this for changing screens
        // throw this if condition into IO manager
        // here temporarily. currently set to on mouse 1 down. need to change to buttons i guess. find someway to detect
        // only single button press (coz on button down is like holding m1 continuosly) maybe find someway to implement a
        // on button up stroke or smth idk
        //
        //************************ IMPORTANT WHEN SWITCHING LEVEL SCENES REMEMER TO CLEAR ENTITYMANAGER ENITIY ARRAY ******************************
//        if (Gdx.input.isTouched()) {
//            gameEngine.setScreen(new MainMenuScreen(gameEngine,batch,font));
//            gameEngine.sceneManager.setCurrentScene("mainmenuscene");
//            dispose();
//            gameEngine.entityManager.clearAllEntities();
//        }
    }
}
