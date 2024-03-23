package com.project.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.math.Rectangle;
import com.project.game.GameEngine;
import com.project.game.Screen.LevelScene;

import java.util.*;
import java.util.logging.Level;

public class EntityManager {
    protected List<Entity> loadedEntities;
    protected int nextID;
    private GameEngine gameEngine;

    protected EntityFactory entityFactory;

    public EntityManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.loadedEntities = new ArrayList<Entity>();
        this.nextID = 0;
        this.entityFactory = new EntityFactory();
    }

    public void createEntity(JsonValue parameters) {
        // entity factory for creating entities
        // factory design pattern requirement for project part 2
        this.loadedEntities.add(entityFactory.createEntity(parameters,this.gameEngine));
    }

    public void updateEntity(String operation, int entityID, Map<String, Object> params) {
        // loop to check for entity ID then edit said entity
        for (Entity entity : loadedEntities) {
            if (entity.getEntityID() == entityID) {
                // depends on what operation needed
                // params defer based on what operation is passed into
                switch (operation) {
                    case ("moveX"):
                        // move by delta position (position increment calculation done at playercontrolmanager
                        // entity blocked movement for screen border control. set at collision manager
                        if (!(entity.getBlockedMovement()[0] && (float) params.get("deltaMovement")<0 || entity.getBlockedMovement()[2] && (float) params.get("deltaMovement")>0)){
                            entity.setPosX(entity.getPos().x + (float) params.get("deltaMovement"));
                            entity.setBlockedMovement(0, false);
                            entity.setBlockedMovement(2, false);
                        }
                        break;
                    case ("moveY"):
                        // same as move X but for Y axis
                        if (!(entity.getBlockedMovement()[3] && (float) params.get("deltaMovement")<0 || entity.getBlockedMovement()[1] && (float) params.get("deltaMovement")>0)){
                            entity.setPosY(entity.getPos().y + (float)params.get("deltaMovement"));
                            entity.setBlockedMovement(3, false);
                            entity.setBlockedMovement(1, false);
                        }
                        break;
                    case ("setBlockedMovement"):
                        // called at collision manager to lock movement
                        entity.setBlockedMovement((int) params.get("id"), (boolean) params.get("state"));
                        break;
                    case ("rotatePlayer"):
                        // called at player control to change the player position
                        Vector2 direction = (Vector2) params.get("direction");
                        entity.setRotation(direction.angleDeg());
                        break;
                    case ("updateHealth"):
                        // called at collision manager
                        ((CharacterEntity) entity).setHealth((int) params.get("health"));
                        break;
                    case ("playerHitFlash"):
                        // called at collision manager
                        ((PlayerEntity) entity).startFlashing();
                        break;
                    default:
                        return;
                }
                return;
            }
        }
    }

    public void deleteEntity(int entityID) {
        for (Iterator<Entity> iter = loadedEntities.listIterator(); iter.hasNext(); ) {
            Entity entity = iter.next();
            if (entity.getEntityID() == entityID) {
                entity.dispose();
                iter.remove();
            }
        }
    }
    public void updateEnemyRotation(){
        // simple wrapper to update all enemy rotation
        // called at AI control manager
        Vector2 playerPos = null;
        for (Entity entity : this.loadedEntities) {
            if (entity.getType().equals("player")){
                playerPos = entity.getPos();
                break;
            }
        }
        if(playerPos != null) {
            for (Entity entity : this.loadedEntities) {
                if (entity.getType().equals("enemy")) {
                    Vector2 direction = new Vector2(playerPos.x - entity.pos.x, playerPos.y - entity.pos.y);
                    entity.setRotation(direction.angleDeg());
                }
            }
        }
    }

    public List<Entity> getLoadedEntity() {
        return this.loadedEntities;
    }
    public int getPlayerEntityId(){
        for (Entity entity : this.loadedEntities) {
            if (entity.getType().equals("player")){
                return entity.getEntityID();
            }
        }
        return -1;
    }
    public Vector2 getPlayerPosition(){
        for (Entity entity : this.loadedEntities) {
            if (entity.getType().equals("player")){
                return entity.getPos();
            }
        }
        return null;
    }

    public float getEntitySpeed(int ID){
        for (Entity entity: this.loadedEntities){
            if (entity.getEntityID() == ID){
                return entity.getSpeed();
            }
        }
        return 0;
    }
    public int getPlayerHealth(){
        for (Entity entity : this.loadedEntities) {
            if (entity.getType().equals("player")){
                PlayerEntity temp = (PlayerEntity) entity;
                return temp.getHealth();
            }
        }
        return 0;
    }

    public void clearAllEntities(){
        for(Entity entity: this.loadedEntities){
            entity.dispose();
        }
        this.loadedEntities = new ArrayList<Entity>();
        this.nextID=0;
    }

    public void setLoadedEntities(List<Entity> loadentity){
        for (Iterator<Entity> iter = loadedEntities.listIterator(); iter.hasNext(); ) {
            Entity entity = iter.next();
            entity.dispose();
            iter.remove();
        }
//        this.nextID=0;
        this.loadedEntities=loadentity;
//        for (Entity entity: this.gameEngine.entityManager.getLoadedEntity()){
//            ((LevelScene)gameEngine.sceneManager.getScreen()).getStage().addActor(entity);
//        }
    }
}
