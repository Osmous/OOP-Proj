package com.project.game;

import com.project.game.Entity.Entity;

import java.util.Map;

public class EntityManager {
    private Entity[] loadedEntities;

    public EntityManager() {
        this.loadedEntities = null;
    }

    public void createEntity(String type, Map<String,String> parameters){
        //Todo
        // dk hard code one if statement for each type of entity? if yall got better idea how to do this pls go ahead
        // ideally just need something to determine what type of entity and and some format to pass the entity's parameters
        // into this fucntion
    }
    public void updateEntity(){
        //todo
    }
    public void deleteEntity(){
        //todo
    }

    public Entity[] getLoadedEntity() {
        return this.loadedEntities;
    }
}
