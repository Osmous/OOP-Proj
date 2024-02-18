package com.project.game;

import com.badlogic.gdx.Input;
import com.project.game.Entity.PlayerEntity;
import com.project.game.GameEngine;

public class PlayerControlManager {
    // Private instances
    private PlayerEntity player;
    private GameEngine gameEngine;
    private float playerSpeed; // Hold player's speed

    // Constructor
    public PlayerControlManager(PlayerEntity player, GameEngine gameEngine, float playerSpeed) {
        this.player = player;
        this.gameEngine = gameEngine;
        this.playerSpeed = playerSpeed; // Initialize player's speed
    }

    // Method to move player left
    private void movePlayerLeft(float delta) {
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = -playerSpeed * delta;
        // Call moveLeft method
        player.moveLeft();
        // Call updateEntity method through gameEngine
        gameEngine.entityManager().updateEntity("moveX", player.getEntityID(), distanceToMove);
    }

    // Method to move player right
    private void movePlayerRight(float delta) {
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = playerSpeed * delta;
        // Call moveRight method
        player.moveRight();
        // Call updateEntity method through gameEngine
        gameEngine.entityManager().updateEntity("moveX", player.getEntityID(), distanceToMove);
    }

    // Method to handle key input and move player accordingly
    public void handleInput(int keyCode, float delta) {
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
