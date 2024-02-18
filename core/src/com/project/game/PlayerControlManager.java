package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.project.game.GameEngine;

public class PlayerControlManager {
    // Private instances
    private GameEngine gameEngine;
    private float playerSpeed; // Hold player's speed

    // Constructor
    public PlayerControlManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    // Method to move player left
    private void movePlayerLeft(float delta) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = -playerSpeed * delta;
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveX", gameEngine.entityManager.getPlayerEntityId(), distanceToMove);
    }

    // Method to move player right
    private void movePlayerRight(float delta) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = playerSpeed * delta;
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveX", gameEngine.entityManager.getPlayerEntityId(), distanceToMove);
    }

    // Method to handle key input and move player accordingly
    public void handleInput(int keyCode) {
        float delta = Gdx.graphics.getDeltaTime();
        switch (keyCode) {
            case Input.Keys.LEFT:
                movePlayerLeft(delta);
                break;
            case Input.Keys.RIGHT:
                movePlayerRight(delta);
                break;
        }
    }
}
