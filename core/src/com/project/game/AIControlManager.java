package com.project.game;

import com.badlogic.gdx.Gdx;
import com.project.game.Entity.EnemyEntity;
import com.project.game.Entity.Entity;
import com.project.game.Entity.ProjectileEntity;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.Map;

public class AIControlManager {
    private GameEngine gameEngine;

    public AIControlManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void updateAI() {
        Vector2 playerPosition = gameEngine.entityManager.getPlayerPosition();
        float delta = Gdx.graphics.getDeltaTime();

        for (Entity entity : gameEngine.entityManager.getLoadedEntity()) {
            if (entity.getType().equals("enemy")) {
                generateEnemyMovement((EnemyEntity) entity, playerPosition, delta);
            } else if (entity.getType().equals("projectile")) {
                updateProjectileMovement((ProjectileEntity) entity, delta);
            }
        }
    }

    private void generateEnemyMovement(EnemyEntity enemy, Vector2 playerPosition, float delta) {
        Vector2 direction = getDirectionFromEnemyToPlayer(enemy);

        // Adjust enemy position based on direction towards the player
        float enemySpeed = enemy.getSpeed();
        float deltaX = direction.x * enemySpeed * delta;
        float deltaY = direction.y * enemySpeed * delta;

        // Update enemy position
        Map<String, Object> data = new HashMap<>();
        data.put("deltaMovement", deltaX);
        gameEngine.entityManager.updateEntity("moveX", enemy.getEntityID(), data);
        data.put("deltaMovement", deltaY);
        gameEngine.entityManager.updateEntity("moveY", enemy.getEntityID(), data);
    }

    private void updateProjectileMovement(ProjectileEntity projectile, float delta) {
        // Update projectile position based on its direction and speed
        float deltaX = projectile.getDirection().x * projectile.getSpeed() * delta;
        float deltaY = projectile.getDirection().y * projectile.getSpeed() * delta;

        // Move the projectile
        projectile.moveBy(deltaX, deltaY);
    }

    private Vector2 getDirectionFromEnemyToPlayer(EnemyEntity enemy) {
        Vector2 playerPosition = gameEngine.entityManager.getPlayerPosition();
        return new Vector2(playerPosition.x - enemy.getPos().x, playerPosition.y - enemy.getPos().y).nor();
    }
}
