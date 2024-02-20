package com.project.game;

import com.badlogic.gdx.Gdx;
import com.project.game.Entity.EnemyEntity;
import com.project.game.Entity.Entity;
import com.project.game.GameEngine;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AIControlManager {
    private GameEngine gameEngine;
    private Random random;

    public AIControlManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.random = new Random();
    }

    public void updateAI() {
        Vector2 playerPosition = gameEngine.entityManager.getPlayerPosition();
        float delta = Gdx.graphics.getDeltaTime();
        for (Entity entity : gameEngine.getEntityManager().getLoadedEntity()) {
            if (entity.getType().equals("enemy")) {
                generateEnemyMovement(entity,playerPosition,delta);
            }
//            generateEnemyAction(enemy);
        }
    }

    private void generateEnemyMovement(Entity enemy,Vector2 playerPosition, float delta) {
        // Calculate direction towards the player
        Vector2 direction = new Vector2(playerPosition.x - enemy.getPos().x, playerPosition.y - enemy.getPos().y);
        direction.nor(); // Normalize the direction vector

        // Adjust enemy position based on direction towards the player
        float enemySpeed = enemy.getSpeed();
        float deltaX = direction.x * enemySpeed * delta;
        float deltaY = direction.y * enemySpeed * delta;

        // Update enemy position
//        enemy.moveBy(deltaX, deltaY);
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", deltaX);
        gameEngine.entityManager.updateEntity("moveX", enemy.getEntityID(),data );
        data.put("deltaMovement", deltaY);
        gameEngine.entityManager.updateEntity("moveY", enemy.getEntityID(),data );
    }


    private void generateEnemyAction(EnemyEntity enemy) {
        // Determine if the enemy should perform an attack action
//        boolean shouldAttack = random.nextBoolean(); // Randomly determine whether to attack or not
//        if (shouldAttack) {
//            // Perform attack action
//            gameEngine.attackPlayer(enemy);
//        }
    }
}
