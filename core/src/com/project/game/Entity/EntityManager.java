package com.project.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonValue;
import com.project.game.Entity.Entity;
import com.project.game.Entity.PlayerEntity;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> loadedEntities;

    public EntityManager() {
        this.loadedEntities = new ArrayList<Entity>();
    }

    public void createEntity(JsonValue parameters) {
        // Todo
        // dk hard code one if statement for each type of entity? if yall got better idea how to do this pls go ahead
        // ideally just need something to determine what type of entity and and some format to pass the entity's parameters
        // into this fucntion
        if (parameters.getString("type").equals("player")) {
            Texture tex = new Texture(Gdx.files.internal(parameters.getString("texturePath")));
            Rectangle rec = new Rectangle();
            rec.height = tex.getHeight();
            rec.width = tex.getWidth();
            PlayerEntity player = new PlayerEntity(parameters.getInt("posX"), parameters.getInt("posY"), parameters.getString("type"), tex, rec);
            this.loadedEntities.add((Entity) player);
        }

        if (parameters.getString("type").equals("enemy")) {
            // place holder maybe enemy change stuffs abit idk
            Texture tex = new Texture(Gdx.files.internal(parameters.getString("texturePath")));
            Rectangle rec = new Rectangle();
            rec.height = tex.getHeight();
            rec.width = tex.getWidth();
            PlayerEntity player = new PlayerEntity(parameters.getInt("posX"), parameters.getInt("posY"), parameters.getString("type"), tex, rec);
            this.loadedEntities.add((Entity) player);
        }
    }

    public void updateEntity(String operation, int entityID, float params) {
        // todo
        for (Entity entity : loadedEntities) {
            if (entity.getEntityID() == entityID) {
                switch (operation) {
                    case ("moveX"):
                        entity.setPosX(entity.getPosX() + params);
                    case ("moveY"):
                        entity.setPosY(entity.getPosY() + params);
                }
                return;
            }
        }
    }
    public void renderEntity(SpriteBatch batch){
        // todo
        batch.begin();
        for (Entity entity : this.loadedEntities) {
            entity.renderEntity(batch);
            //this shd be placed into io manager/ control managers but here just for now
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && entity.getType().equals("player"))
                entity.setPosX((entity.getPosX() - 200 * Gdx.graphics.getDeltaTime()));
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && entity.getType().equals("player"))
                entity.setPosX((entity.getPosX() + 200 * Gdx.graphics.getDeltaTime()));

            if (Gdx.input.isKeyPressed(Input.Keys.UP) && entity.getType().equals("enemy"))
                entity.setPosY((entity.getPosY() + 200 * Gdx.graphics.getDeltaTime()));
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && entity.getType().equals("enemy"))
                entity.setPosY((entity.getPosY() - 200 * Gdx.graphics.getDeltaTime()));
        }
        batch.end();
    }

    public void deleteEntity() {
        // todo
        // this for deleting single entities
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

    public void clearAllEntities(){
        for(Entity entity: this.loadedEntities){
            entity.dispose();
        }
        this.loadedEntities = new ArrayList<Entity>();
    }
}
