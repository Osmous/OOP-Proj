//package com.project.game;

//public class IOManager {
    //public IOManager() {
        // see gameengine render() for more like help info if u need
    //}
//}

package com.project.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class IOManager implements InputProcessor {
    private GameEngine gameEngine;

    public IOManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        
        Input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        
        if (keycode == Input.Keys.LEFT) {
            gameEngine.playerControlManager.movePlayerLeft();
        } else if (keycode == Input.Keys.RIGHT) {
            gameEngine.playerControlManager.movePlayerRight();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        // Handle key release events
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        // Handle character input events
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Handle touch down events
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Handle touch up events
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Handle touch drag events
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // Handle mouse move events
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // Handle scroll events
        return true;
    }
}