package com.project.game;

import com.badlogic.gdx.Gdx;
import com.project.game.Entity.Entity;
import com.project.game.Entity.EntityManager;

import java.util.*;

public class CollisionManager {

    private GameEngine gameEngine;
    private EntityManager entityManager;


    public CollisionManager(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.entityManager = gameEngine.entityManager;
    }

    public void checkCollisions() {
            List<Entity> entities = new ArrayList<>(this.gameEngine.entityManager.getLoadedEntity()); // Create a copy of the list
            for (Entity entity : entities) {
                // Your collision detection logic goes here

            // Checking Collision between projectile and Screen Boundaries
           if (entity.getType().equals("projectile")) {
               if (entity.getPos().x < 0 || entity.getPos().x + entity.getSprite().getWidth() >= Gdx.graphics.getWidth() || entity.getPos().y < 0 || entity.getPos().y + entity.getSprite().getHeight() >= Gdx.graphics.getHeight())
               {
                   //Delete projectile entity if it goes out of bounds
                   entityManager.deleteEntity(entity.getEntityID());
               }
           }
            // Check for collision with screen boundaries
            if (entity.getPos().x < 0) {
                handleCollisionWithScreenBoundary(entity, "left");
            } else if (entity.getPos().x + entity.getSprite().getWidth() >= Gdx.graphics.getWidth()) {
                handleCollisionWithScreenBoundary(entity, "right");
            }
            if (entity.getPos().y < 0) {
                handleCollisionWithScreenBoundary(entity, "bottom");
            } else if (entity.getPos().y + entity.getSprite().getHeight() >= Gdx.graphics.getHeight()) {
                handleCollisionWithScreenBoundary(entity, "top");
            }

            // Check for collision with other entities
            for (Entity otherEntity : entities) {
                if (entity.getEntityID() != otherEntity.getEntityID()) {
                    if (checkCollision(entity, otherEntity)) {
                        handleCollision(entity, otherEntity);
                    }
                }
            }
        }
    }

    private boolean checkCollision(Entity entity1, Entity entity2) {
        return entity1.getPos().x < entity2.getPos().x + entity2.getSprite().getWidth() &&
                entity1.getPos().x + entity1.getSprite().getWidth() > entity2.getPos().x &&
                entity1.getPos().y < entity2.getPos().y + entity2.getSprite().getHeight() &&
                entity1.getPos().y + entity1.getSprite().getHeight() > entity2.getPos().y;
    }

    private void handleCollision(Entity entity1, Entity entity2) {
        long currentTime = System.currentTimeMillis();
        long entity1NextHitTime = entity1.getNextHitTime();


        // Check if the player entity is on cooldown based on next hit time
        if (entity1.getType().equals("player") && entity1NextHitTime > currentTime) {
            return; // Player entity is still on cooldown, ignore collision
        }

        // Player entity's health reaches zero
        if (entity1.getType().equals("player")) {
            if (entity2.getType().equals("projectile") || entity2.getType().equals("enemy")) {
                // Damage the player entity
                int playerHealth = this.gameEngine.entityManager.getPlayerHealth();
                playerHealth--;
                Map<String, Object> healthData = new HashMap<>();
                healthData.put("health", playerHealth);
                entityManager.updateEntity("updateHealth", entity1.getEntityID(), healthData);

                if (playerHealth <= 0) {
                    // Delete the player entity immediately
                    this.gameEngine.entityManager.deleteEntity(entity1.getEntityID());
                    }
                }
            }

        // Player Projectile collide with enemy
        else if (entity1.getType().equals("projectile") && entity2.getType().equals("enemy")) {
            // Mark the enemy entity for deletion
            entityManager.deleteEntity(entity1.getEntityID());
            entityManager.deleteEntity(entity2.getEntityID());
        }

        // Set cooldown for the player entity
        if (entity1.getType().equals("player")) {
            int cooldownDuration = 2000; // Cooldown duration in milliseconds (adjust as needed)
            entity1.setNextHitTime(currentTime + cooldownDuration);
        }
    }


    private void handleCollisionWithScreenBoundary(Entity entity, String boundary) {
        Map<String, Object> data = new HashMap<>();
        switch (boundary) {
            case "left":
                data.put("id", 0);
                break;
            case "right":
                data.put("id", 2);
                break;
            case "bottom":
                data.put("id", 3);
                break;
            case "top":
                data.put("id", 1);
                break;
        }
        data.put("state", true); // Set the corresponding direction as blocked
        entityManager.updateEntity("setBlockedMovement", entity.getEntityID(), data);
    }
}
