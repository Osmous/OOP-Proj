package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

    public void updateEntity() {
        // todo
    }

    public void deleteEntity() {
        // todo
        // this for deleting single entities
    }

    public List<Entity> getLoadedEntity() {
        return this.loadedEntities;
    }

    public void clearAllEntities(){
        for(Entity entity: this.loadedEntities){
            entity.dispose();
        }
        this.loadedEntities = new ArrayList<Entity>();
    }
}
