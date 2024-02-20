package com.project.game;

import com.project.game.Entity.EnemyEntity;
import com.project.game.GameEngine;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class AIControlManager {
    private GameEngine gameEngine;
    private Random random;

    public AIControlManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.random = new Random();
    }

    public void updateAI() {
        for (EnemyEntity enemy : gameEngine.getEntityManager().getLoadedEntity()) {
            generateEnemyMovement(enemy);
            generateEnemyAction(enemy);
        }
    }

    private void generateEnemyMovement(EnemyEntity enemy) {
        // Get player position
        Vector2 playerPosition = gameEngine.getEntityManager().getPlayerPosition();

        // Calculate direction towards the player
        Vector2 direction = new Vector2(playerPosition.x - enemy.getPosition().x, playerPosition.y - enemy.getPosition().y);
        direction.nor(); // Normalize the direction vector

        // Adjust enemy position based on direction towards the player
        float enemySpeed = enemy.getSpeed();
        float deltaX = direction.x * enemySpeed;
        float deltaY = direction.y * enemySpeed;

        // Update enemy position
        enemy.moveBy(deltaX, deltaY);
    }

    private void generateEnemyAction(EnemyEntity enemy) {
        // Determine if the enemy should perform an attack action
        boolean shouldAttack = random.nextBoolean(); // Randomly determine whether to attack or not
        if (shouldAttack) {
            // Perform attack action
            gameEngine.attackPlayer(enemy);
        }
    }
}
