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

    private GameEngine gameEngine;
    private EntityManager entityManager;

    public CollisionManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.entityManager = gameEngine.getEntityManager();
    }

    public void checkCollisions() {
        List<Entity> entities = entityManager.getLoadedEntity();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity1 = entities.get(i);

            // Check for collision with screen boundaries
            if (entity1.getPos().x < 0) {
                handleCollisionWithScreenBoundary(entity1, "left");
            } else if (entity1.getPos().x + entity1.getRec().getWidth()  >= Gdx.graphics.getWidth()) {
                handleCollisionWithScreenBoundary(entity1, "right");
            }
            if (entity1.getPos().y < 0) {
                handleCollisionWithScreenBoundary(entity1, "bottom");
            } else if (entity1.getPos().y + entity1.getRec().getHeight() > Gdx.graphics.getHeight()) {
                handleCollisionWithScreenBoundary(entity1, "top");
            }

            // Check for collision with other entities
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity2 = entities.get(j);

                // Skip if it's the same entity
                if (entity1.getEntityID() == entity2.getEntityID()) {
                    continue;
                }

                // Check for collision based on x and y coordinates
                if (entity1.getPos().x < entity2.getPos().x + entity2.getRec().getWidth() &&
                        entity1.getPos().x + entity1.getRec().getWidth() > entity2.getPos().x &&
                        entity1.getPos().y < entity2.getPos().y + entity2.getRec().getHeight() &&
                        entity1.getPos().y + entity1.getRec().getHeight() > entity2.getPos().y) {
                    // Handle collision between entity1 and entity2
                    handleCollision(entity1, entity2);
                }
            }
        }
    }


    private void handleCollision(Entity entity1, Entity entity2) {
        // Print a message
        System.out.println("Collision detected between entity " + entity1.getEntityID() + " and entity " + entity2.getEntityID());

        // Calculate the direction of the push ( Calculating the difference in x and y positions )
        float dx = entity2.getPos().x - entity1.getPos().x;
        float dy = entity2.getPos().y - entity1.getPos().y;

        // Normalize the direction (Calculate the length of this vector using pythagorean theorem)
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length != 0) { // Avoid division by zero
            dx /= length;
            dy /= length;
        }

        // Apply the push to the entities' velocities
        float pushStrength = 2.0f;

        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", (-dx * pushStrength));
        entityManager.updateEntity("moveX", entity1.getEntityID(), data);
        data.put("deltaMovement", (-dy * pushStrength));
        entityManager.updateEntity("moveY", entity1.getEntityID(), data);
        data.put("deltaMovement", (dx * pushStrength));
        entityManager.updateEntity("moveX", entity2.getEntityID(), data);
        data.put("deltaMovement", (dy * pushStrength));
        entityManager.updateEntity("moveY", entity2.getEntityID(), data);
    }
    private void handleCollisionWithScreenBoundary(Entity entity, String boundary) {
        // Print a message
        System.out.println("Entity " + entity.getEntityID() + " hit the " + boundary + " boundary of the screen");

        // Calculate the direction of the push
        float dx = 0, dy = 0;
        float pushStrength = 2.0f;
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
        data.put("state", true);
        entityManager.updateEntity("setBlockedMovement", entity.getEntityID(), data);

//        Map<String, Object> data = new HashMap<>();
//        data.put("deltaMovement", dx);
//        // Apply the push to the entity's velocity
//        entityManager.updateEntity("moveX", entity.getEntityID(), data);
//        data.put("deltaMovement", dy);
//        entityManager.updateEntity("moveY", entity.getEntityID(), data);
    }
}

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
