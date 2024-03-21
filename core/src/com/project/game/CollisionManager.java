package com.project.game;

import com.badlogic.gdx.Gdx;
import com.project.game.Entity.Entity;
import com.project.game.Entity.EntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CollisionManager {

    private GameEngine gameEngine;
    private EntityManager entityManager;
    private Map<Integer, Long> collisionCooldowns = new HashMap<>(); // Map to store collision cool down times for each entity

    public CollisionManager(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.entityManager = gameEngine.entityManager;
    }

    public void checkCollisions() {
        List<Entity> entities = this.gameEngine.entityManager.getLoadedEntity();
        for (Entity entity : entities) {
            // This part is also crashing
            // Checking Collision between projectile and Screen Boundaries
          //  if (entity.getType().equals("projectile")) {
          //      if (entity.getPos().x < 0 || entity.getPos().x + entity.getSprite().getWidth() >= Gdx.graphics.getWidth() ||
          //              entity.getPos().y < 0 || entity.getPos().y + entity.getSprite().getHeight() >= Gdx.graphics.getHeight()) {
                    // Delete projectile entity if it goes out of bounds
           //         entityManager.deleteEntity(entity.getEntityID());
           //     }
          //  }
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

        // Check if the player entity is on cooldown
        if (entity1.getType().equals("player") && collisionCooldowns.containsKey(entity1.getEntityID()) && collisionCooldowns.get(entity1.getEntityID()) > currentTime) {
            return; // Player entity is still on cooldown, ignore collision
        }

        // Enemy or Projectile collide with Player
        if (entity1.getType().equals("player")) {
            if (entity2.getType().equals("projectile") || entity2.getType().equals("enemy")) {
                // Damage the player entity
                int playerHealth = this.gameEngine.entityManager.getPlayerHealth();
                playerHealth--;
                Map<String, Object> healthData = new HashMap<>();
                healthData.put("health", playerHealth);
                entityManager.updateEntity("updateHealth", entity1.getEntityID(), healthData);

                if (playerHealth <= 0) {
                    entityManager.deleteEntity(entity1.getEntityID());
                }
            }
    }

        // This part is causing the application to crash when projectile and enemy entity collide
        // Player Projectile collide with enemy
        else if (entity1.getType().equals("projectile")) {
           if (entity2.getType().equals("enemy")) {
                // Delete player bullet entity
                //entityManager.deleteEntity(entity1.getEntityID());

                // Delete enemy entity
                entityManager.deleteEntity(entity2.getEntityID());
           }
        }

        // Set cooldown for both entities
        int cooldownDuration = 2000; // Cooldown duration in milliseconds (adjust as needed)
        collisionCooldowns.put(entity1.getEntityID(), currentTime + cooldownDuration);
        collisionCooldowns.put(entity2.getEntityID(), currentTime + cooldownDuration);
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
