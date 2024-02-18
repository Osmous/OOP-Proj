package com.project.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class IOManager implements InputProcessor {
    private GameEngine gameEngine;
    private boolean isButtonPressed;

    public IOManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        //Input.setInputProcessor(this);
        this.isButtonPressed = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            //gameEngine.playerControlManager.movePlayerLeft();
        } else if (keycode == Input.Keys.RIGHT) {
            //gameEngine.playerControlManager.movePlayerRight();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isButtonPressed) {
            isButtonPressed = true;
            // Handle the click event here
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isButtonPressed) {
            isButtonPressed = false;
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return true;
    }
}