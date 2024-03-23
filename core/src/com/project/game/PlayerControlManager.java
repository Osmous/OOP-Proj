package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerControlManager {
    // Private instances
    private final GameEngine gameEngine;
    private float playerSpeed; // Hold player's speed
    private Vector2 playerPos; // Player's position

    // Constructor
    public PlayerControlManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.playerPos = gameEngine.entityManager.getPlayerPosition(); // Initialize player position
    }

    // Method to move player left
    private void movePlayerLeft(float delta, Vector2 playerPos) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = -playerSpeed * delta;
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", distanceToMove);
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveX", gameEngine.entityManager.getPlayerEntityId(), data);
    }

    // Method to move player right
    private void movePlayerRight(float delta, Vector2 playerPos) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = playerSpeed * delta;
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", distanceToMove);
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveX", gameEngine.entityManager.getPlayerEntityId(), data);
    }

    private void movePlayerDown(float delta, Vector2 playerPos) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = -playerSpeed * delta;
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", distanceToMove);
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveY", gameEngine.entityManager.getPlayerEntityId(), data);
    }

    // Method to move player right
    private void movePlayerUp(float delta, Vector2 playerPos) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = playerSpeed * delta;
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", distanceToMove);
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveY", gameEngine.entityManager.getPlayerEntityId(), data);
    }

    // Shoot function (create a projectile entity)
    public void shoot(Vector2 mousePosition) {
        // Get projectile data as JsonValue object
        JsonValue projectileData = this.gameEngine.config.get("projectileData");

        // Edit the JsonValue projectileData
        (projectileData.get("posX")).set(String.valueOf(playerPos.x));
        (projectileData.get("posY")).set(String.valueOf(playerPos.y));
        (projectileData.get("mousePosX")).set(String.valueOf(mousePosition.x));
        (projectileData.get("mousePosY")).set(String.valueOf(mousePosition.y));

        // Create projectile entity at the player's position with the updated projectile data
        gameEngine.entityManager.createEntity(playerPos, mousePosition, projectileData);
    }
    
        // Method to handle key input and move player accordingly
        // Takes additional parameter of playerPos of type Vector2 to represent the position of the player obtained from entityManager.getPlayerPos() method
        public void handleInput(List<String> keyCodeList) {
            float delta = Gdx.graphics.getDeltaTime();
            // Update player position
            this.playerPos = gameEngine.entityManager.getPlayerPosition();
            // add direction vector2 here
            Vector2 direction = new Vector2(0,0);
            for (String keyCode: keyCodeList){
                switch (Integer.parseInt(keyCode)) {
                    case Input.Keys.LEFT:
                        direction.x = direction.x-1;
                        movePlayerLeft(delta, playerPos);
                        break;
                    case Input.Keys.RIGHT:
                        direction.x = direction.x+1;
                        movePlayerRight(delta, playerPos);
                        break;
                    case Input.Keys.UP:
                        direction.y = direction.y+1;
                        movePlayerUp(delta, playerPos);
                        break;
                    case Input.Keys.DOWN:
                        direction.y = direction.y -1;
                        movePlayerDown(delta, playerPos);
                        break;
                }
            }
        Map<String, Object> data = new HashMap<>();
        data.put("direction", direction);
        gameEngine.entityManager.updateEntity("rotatePlayer", gameEngine.entityManager.getPlayerEntityId(), data);

    }
}