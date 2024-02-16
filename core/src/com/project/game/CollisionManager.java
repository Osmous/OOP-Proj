package com.project.game;

import com.project.game.Entity.Entity;
import com.project.game.Entity.PlayerEntity;
import com.project.game.Entity.EnemyEntity;
import java.util.List;

public class CollisionManager {

    // Check if two entities are colliding by checking their rectangle (positions and sizes)
    public boolean checkCollision(Entity entity1, Entity entity2) {
        return entity1.getRectangle().overlaps(entity2.getRectangle());
    }

    //Checks for collisions between pairs of entities, if there is collision detected , it will call handleCollision
    public void handleCollisions(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (checkCollision(entities.get(i), entities.get(j))) {
                    handleCollision(entities.get(i), entities.get(j));
                }
            }
        }
    }

    // Handles a collision between two entities.
    // If entities are a player and an enemy, it calls the handlePlayerEnemyCollision
    private void handleCollision(Entity entity1, Entity entity2) {
        if (entity1 instanceof PlayerEntity && entity2 instanceof EnemyEntity) {
            handlePlayerEnemyCollision((PlayerEntity) entity1, (EnemyEntity) entity2);
        } else if (entity1 instanceof EnemyEntity && entity2 instanceof PlayerEntity) {
            handlePlayerEnemyCollision((PlayerEntity) entity2, (EnemyEntity) entity1);
        }
    }

    private void handlePlayerEnemyCollision(PlayerEntity player, EnemyEntity enemy) {
        // Define what happens when a player collides with an enemy
        // For example, you might decrease the player's health or end the game
        System.out.println("Player collided with an enemy!");
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



