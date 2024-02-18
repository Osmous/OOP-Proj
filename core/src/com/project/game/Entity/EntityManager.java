package com.project.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonValue;
import com.project.game.Entity.EnemyEntity;
import com.project.game.Entity.Entity;
import com.project.game.Entity.PlayerEntity;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private List<Entity> loadedEntities;
    private int nextID;

    public EntityManager() {
        this.loadedEntities = new ArrayList<Entity>();
        this.nextID = 0;
    }

    public void createEntity(JsonValue parameters) {
        // Todo
        // dk hard code one if statement for each type of entity? if yall got better idea how to do this pls go ahead
        // ideally just need something to determine what type of entity and and some format to pass the entity's parameters
        // into this fucntion
        Texture tex;
        Rectangle rec;
        switch (parameters.getString("type")){
            case ("player"):
                tex = new Texture(Gdx.files.internal(parameters.getString("texturePath")));
                rec = new Rectangle();
                rec.height = tex.getHeight();
                rec.width = tex.getWidth();
                PlayerEntity player = new PlayerEntity(this.nextID, parameters.getFloat("posX"), parameters.getFloat("posY"),
                        parameters.getString("type"), tex, rec,parameters.getFloat("posX"));
                this.loadedEntities.add((Entity) player);
            case ("enemy"):
                // place holder maybe enemy change stuffs abit idk
                tex = new Texture(Gdx.files.internal(parameters.getString("texturePath")));
                rec = new Rectangle();
                rec.height = tex.getHeight();
                rec.width = tex.getWidth();
                EnemyEntity enemy = new EnemyEntity(this.nextID, parameters.getFloat("posX"), parameters.getFloat("posY"),
                        parameters.getString("type"), tex, rec,parameters.getFloat("posX"));
                this.loadedEntities.add((Entity) enemy);
        }
        this.nextID++;
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

    public void deleteEntity(int entityID) {
        // todo
//        loadedEntities.removeIf(entity -> entity.getEntityID() == entityID);

        for (Iterator<Entity> iter = loadedEntities.listIterator(); iter.hasNext(); ) {
            Entity entity = iter.next();
            if (entity.getEntityID() == entityID) {
                iter.remove();
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

    public void clearAllEntities(){
        for(Entity entity: this.loadedEntities){
            entity.dispose();
        }
        this.loadedEntities = new ArrayList<Entity>();
        this.nextID=0;
    }
}
