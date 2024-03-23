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

    // Constructor
    public PlayerControlManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    // Method to move player left
    private void movePlayerLeft(float delta) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = -playerSpeed * delta;
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", distanceToMove);
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveX", gameEngine.entityManager.getPlayerEntityId(), data);
    }

    // Method to move player right
    private void movePlayerRight(float delta) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = playerSpeed * delta;
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", distanceToMove);
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveX", gameEngine.entityManager.getPlayerEntityId(), data);
    }

    private void movePlayerDown(float delta) {
        playerSpeed = gameEngine.entityManager.getEntitySpeed(gameEngine.entityManager.getPlayerEntityId());
        // Calculate distance to move based on player's speed and delta
        float distanceToMove = -playerSpeed * delta;
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", distanceToMove);
        // Call updateEntity method through gameEngine
        gameEngine.entityManager.updateEntity("moveY", gameEngine.entityManager.getPlayerEntityId(), data);
    }

    // Method to move player right
    private void movePlayerUp(float delta) {
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
        Vector2 playerPos = this.gameEngine.entityManager.getPlayerPosition();
        this.gameEngine.ioManager.playSound("shoot");
        // Edit the JsonValue projectileData
        (projectileData.get("posX")).set(String.valueOf(playerPos.x));
        (projectileData.get("posY")).set(String.valueOf(playerPos.y));
        (projectileData.get("mousePosX")).set(String.valueOf(mousePosition.x));
        (projectileData.get("mousePosY")).set(String.valueOf(mousePosition.y));

        // Create projectile entity at the player's position with the updated projectile data
        gameEngine.entityManager.createEntity(projectileData);
    }
    
    // Method to handle key input and move player accordingly
    // Takes additional parameter of playerPos of type Vector2 to represent the position of the player obtained from entityManager.getPlayerPos() method
    public void handleInput(List<String> keyCodeList) {
        float delta = Gdx.graphics.getDeltaTime();
        // add direction vector2 here. this to determine the rotation of player based on inputs
        Vector2 direction = new Vector2(0,0);
        for (String keyCode: keyCodeList){
            switch (Integer.parseInt(keyCode)) {
                case Input.Keys.A:
                    direction.x = direction.x-1;
                    movePlayerLeft(delta);
                    break;
                case Input.Keys.D:
                    direction.x = direction.x+1;
                    movePlayerRight(delta);
                    break;
                case Input.Keys.W:
                    direction.y = direction.y+1;
                    movePlayerUp(delta);
                    break;
                case Input.Keys.S:
                    direction.y = direction.y -1;
                    movePlayerDown(delta);
                    break;
            }
        }
        // if direction is not 0 update roation, meaning only if there is change to the movement then change rotation.
        // or else the player will keep resetting to rotation 0 angle when no key is pressed. so ugly
        if(!direction.isZero()){
            Map<String, Object> data = new HashMap<>();
            data.put("direction", direction);
            gameEngine.entityManager.updateEntity("rotatePlayer", gameEngine.entityManager.getPlayerEntityId(), data);
        }
    }
}