package com.project.game;

import com.badlogic.gdx.Input;
import com.project.game.Entity.EntityManager;
import com.project.game.Entity.PlayerEntity;

public class PlayerControlManager {
    // Private instances
    private PlayerEntity player;
    private EntityManager entityManager;
    private float playerSpeed; // Hold player's speed

    // Constructor
    public PlayerControlManager(PlayerEntity player, EntityManager entityManager, float playerSpeed) {
        this.player = player;
        this.entityManager = entityManager;
        this.playerSpeed = playerSpeed; // Initialize player's speed
    }

    // Method to move player left
    private void movePlayerLeft(float delta) {
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = -playerSpeed * delta;
        // Call moveLeft method and update entity's position using the updateEntity method in EntityManager
        player.moveLeft();
        entityManager.updateEntity("moveX", player.getEntityID(), distanceToMove);
    }

    // Method to move player right
    private void movePlayerRight(float delta) {
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = playerSpeed * delta;
        // Call moveRight method and update entity's position using the updateEntity method in EntityManager
        player.moveRight();
        entityManager.updateEntity("moveX", player.getEntityID(), distanceToMove);
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
