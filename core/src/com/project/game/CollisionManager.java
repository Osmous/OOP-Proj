package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.project.game.Entity.Entity;
import com.project.game.Entity.EntityManager;
import com.project.game.GameEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CollisionManager {

    private EntityManager entityManager;
    private GameEngine gameEngine;
    private Map<Integer, Long> collisionCooldowns = new HashMap<>(); // Map to store collision cool down times for each entity

    public CollisionManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.entityManager = gameEngine.entityManager;

    }

    public void checkCollisions() {
        List<Entity> entities = entityManager.getLoadedEntity();
        for (Entity entity : entities) {
            // Check for collision with screen boundaries
            if (entity.getPos().x < 0) {
                handleCollisionWithScreenBoundary(entity, "left");
            } else if (entity.getPos().x + entity.getRec().getWidth() >= Gdx.graphics.getWidth()) {
                handleCollisionWithScreenBoundary(entity, "right");
            }
            if (entity.getPos().y < 0) {
                handleCollisionWithScreenBoundary(entity, "bottom");
            } else if (entity.getPos().y + entity.getRec().getHeight() > Gdx.graphics.getHeight()) {
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
        return entity1.getPos().x < entity2.getPos().x + entity2.getRec().getWidth() &&
                entity1.getPos().x + entity1.getRec().getWidth() > entity2.getPos().x &&
                entity1.getPos().y < entity2.getPos().y + entity2.getRec().getHeight() &&
                entity1.getPos().y + entity1.getRec().getHeight() > entity2.getPos().y;
    }

    private void handleCollision(Entity entity1, Entity entity2) {
        long currentTime = System.currentTimeMillis();

        // Check if the player entity is on cool down
        if (entity1.getType().equals("player") && collisionCooldowns.containsKey(entity1.getEntityID()) && collisionCooldowns.get(entity1.getEntityID()) > currentTime) {
            return; // Player entity is still on cool down, ignore collision
        }

        // Handle collision logic based on entity types
        if (entity1.getType().equals("player")) {
            if (entity2.getType().equals("enemyBullet") || entity2.getType().equals("enemy")) {
                // Damage the player entity (player health -1 ??)
                // int playerHealth = entity1.getHealth();
                // playerHealth -= 1;
                // entity1.setHealth(playerHealth);
                // if (playerHealth <=0 ){
                // entityManager.deleteEntity(entity1.getEntityID())
                // };
            }
        } else if (entity1.getType().equals("playerBullet")) {
            if (entity2.getType().equals("enemy")) {
                // Delete player bullet entity
                // entityManager.deleteEntity(entity1.getEntityID());

                // Delete enemy entity
                // entityManager.deleteEntity(entity2.getEntityID());
            }
        }

        // Set cool down for both entities
        int cooldownDuration = 3000; // Cooldown duration in milliseconds (adjust as needed)
        collisionCooldowns.put(entity1.getEntityID(), currentTime + cooldownDuration);
        collisionCooldowns.put(entity2.getEntityID(), currentTime + cooldownDuration);
    }


    private void handleCollisionWithScreenBoundary(Entity entity, String boundary) {
        // Print a message
        System.out.println("Entity " + entity.getEntityID() + " hit the " + boundary + " boundary of the screen");

        // Stop the entity's movement in the direction of the boundary
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

//        Map<String, Object> data = new HashMap<>();
//        data.put("deltaMovement", dx);
//        // Apply the push to the entity's velocity
//        entityManager.updateEntity("moveX", entity.getEntityID(), data);
//        data.put("deltaMovement", dy);
//        entityManager.updateEntity("moveY", entity.getEntityID(), data);

// every entity has a rectangle. look into checking if the rectangle overlaps with another rectangle
        // somehow uh think of an optimised way to check thru every entity with every other entity in the list
        // so eg [1,2,3,4]
        // 1 has to check 2,3,4
        // 2 has to check 3,4
        // 3 has to check 4
        // 4 dunnid check anything
        // do this every frame or smth
        // this example ah but if u can think of a better way to do it go a head.
        // plan for a lot of entities in the entity list
//    }
//}
