package com.project.game.Entity;

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
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity1 = entities.get(i);
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
        entity1.setPosX(entity1.getPosX() - 2);
        entity1.setPosY(entity1.getPosY() + 2);
        entity2.setPosX(entity2.getPosX() + 2);
        entity2.setPosY(entity2.getPosY() - 2);
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



