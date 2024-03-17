package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Method to handle key input and move player accordingly
    public void handleInput(List<String> keyCodeList) {
        float delta = Gdx.graphics.getDeltaTime();
        // add direction vector2 here
        Vector2 direction = new Vector2(0,0);
        for (String keyCode: keyCodeList){
            switch (Integer.parseInt(keyCode)) {
                case Input.Keys.LEFT:
                    direction.x = direction.x-1;
                    movePlayerLeft(delta);
                    break;
                case Input.Keys.RIGHT:
                    direction.x = direction.x+1;
                    movePlayerRight(delta);
                    break;
                case Input.Keys.UP:
                    direction.y = direction.y+1;
                    movePlayerUp(delta);
                    break;
                case Input.Keys.DOWN:
                    direction.y = direction.y -1;
                    movePlayerDown(delta);
                    break;
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("direction", direction);
        gameEngine.entityManager.updateEntity("rotatePlayer", gameEngine.entityManager.getPlayerEntityId(), data);

    }


}