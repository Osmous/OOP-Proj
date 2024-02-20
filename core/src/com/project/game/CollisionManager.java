package com.project.game;

import com.badlogic.gdx.Gdx;
import com.project.game.Entity.Entity;
import com.project.game.Entity.EntityManager;
import com.project.game.GameEngine;

import java.util.List;

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
            if (entity1.getPosX() < 0) {
                handleCollisionWithScreenBoundary(entity1, "left");
            } else if (entity1.getPosX() + entity1.getRec().getWidth()  > Gdx.graphics.getWidth()) {
                handleCollisionWithScreenBoundary(entity1, "right");
            }
            if (entity1.getPosY() < 0) {
                handleCollisionWithScreenBoundary(entity1, "bottom");
            } else if (entity1.getPosY() + entity1.getRec().getHeight() > Gdx.graphics.getHeight()) {
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
                if (entity1.getPosX() < entity2.getPosX() + entity2.getRec().getWidth() &&
                        entity1.getPosX() + entity1.getRec().getWidth() > entity2.getPosX() &&
                        entity1.getPosY() < entity2.getPosY() + entity2.getRec().getHeight() &&
                        entity1.getPosY() + entity1.getRec().getHeight() > entity2.getPosY()) {
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
        float dx = entity2.getPosX() - entity1.getPosX();
        float dy = entity2.getPosY() - entity1.getPosY();

        // Normalize the direction (Calculate the length of this vector using pythagorean theorem)
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length != 0) { // Avoid division by zero
            dx /= length;
            dy /= length;
        }

        // Apply the push to the entities' velocities
        float pushStrength = 2.0f;
        entityManager.updateEntity("moveX", entity1.getEntityID(), -dx * pushStrength);
        entityManager.updateEntity("moveY", entity1.getEntityID(), -dy * pushStrength);
        entityManager.updateEntity("moveX", entity2.getEntityID(), dx * pushStrength);
        entityManager.updateEntity("moveY", entity2.getEntityID(), dy * pushStrength);
    }
    private void handleCollisionWithScreenBoundary(Entity entity, String boundary) {
        // Print a message
        System.out.println("Entity " + entity.getEntityID() + " hit the " + boundary + " boundary of the screen");

        // Calculate the direction of the push
        float dx = 0, dy = 0;
        float pushStrength = 2.0f;
        switch (boundary) {
            case "left":
                dx = pushStrength;
                break;
            case "right":
                dx = -pushStrength;
                break;
            case "bottom":
                dy = pushStrength;
                break;
            case "top":
                dy = -pushStrength;
                break;
        }

        // Apply the push to the entity's velocity
        entityManager.updateEntity("moveX", entity.getEntityID(), dx);
        entityManager.updateEntity("moveY", entity.getEntityID(), dy);
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



