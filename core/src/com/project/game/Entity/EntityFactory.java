package com.project.game.Entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.project.game.GameEngine;
import com.project.game.Screen.LevelScene;

public class EntityFactory {
    public Entity createEntity(JsonValue parameters, GameEngine gameEngine){
        Texture tex;
        Rectangle rec;
        Entity entity;
        switch (parameters.getString("type")){
            case ("player"):
                tex = new Texture(Gdx.files.local(parameters.getString("texturePath")));
                rec = new Rectangle();
                rec.height = tex.getHeight();
                rec.width = tex.getWidth();
                entity = new PlayerEntity(gameEngine.entityManager.nextID, new Vector2(parameters.getFloat("posX"), parameters.getFloat("posY")),
                        parameters.getString("type"), tex, rec,parameters.getFloat("speed"),parameters.getInt("health"));
                break;
            case ("enemy"):
                tex = new Texture(Gdx.files.local(parameters.getString("texturePath")));
                rec = new Rectangle();
                rec.height = tex.getHeight();
                rec.width = tex.getWidth();
                entity = new EnemyEntity(gameEngine.entityManager.nextID,new Vector2(parameters.getFloat("posX"), parameters.getFloat("posY")),
                        parameters.getString("type"), tex, rec,parameters.getFloat("speed"),parameters.getInt("health"));
                break;
            case ("projectile"):
                tex = new Texture(Gdx.files.local(parameters.getString("texturePath")));
                rec = new Rectangle();
                rec.height = tex.getHeight();
                rec.width = tex.getWidth();
                entity = new ProjectileEntity(gameEngine.entityManager.nextID,new Vector2(parameters.getFloat("posX"), parameters.getFloat("posY")),
                        parameters.getString("type"), tex, rec,parameters.getFloat("speed"), new Vector2(parameters.getFloat("mousePosX"), parameters.getFloat("mousePosY")));
                break;
            default:
                return null;
        }
        ((LevelScene)gameEngine.sceneManager.getScreen()).getStage().addActor(entity);
        gameEngine.entityManager.nextID++;
        return entity;
    }
}
