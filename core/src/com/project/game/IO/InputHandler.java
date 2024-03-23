package com.project.game.IO;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.project.game.Entity.Entity;
import com.project.game.GameEngine;
import com.project.game.Screen.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputHandler extends InputAdapter {
    private GameEngine gameEngine;
    private List<String> keypressedlist;
    //    private boolean isButtonPressed;

    public InputHandler(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.keypressedlist = new ArrayList<String>();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING")){
            keypressedlist.add(Integer.toString(keycode));
        }
        if(keycode == com.badlogic.gdx.Input.Keys.ESCAPE && (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING") || this.gameEngine.simulationCycleManager.getCurrentState().equals("PAUSE") )){
            gameEngine.simulationCycleManager.pauseGame();
        }

        //FOR TESTING PURPOSE
        if(keycode == com.badlogic.gdx.Input.Keys.P && this.gameEngine.sceneManager.getCurrentScene().equals("levelscene")){
            List<Integer> holder = new ArrayList<Integer>();
            for(Entity entity : this.gameEngine.entityManager.getLoadedEntity()){
                if (entity.getType().equals("enemy")) {
                    holder.add(entity.getEntityID());
                }
            }
            for(Integer id : holder){
                this.gameEngine.entityManager.deleteEntity(id);
            }
        }
        //FOR TESTING PURPOSE
        if(keycode == com.badlogic.gdx.Input.Keys.L && this.gameEngine.sceneManager.getCurrentScene().equals("levelscene")){
            Map<String, Object> data = new HashMap<>();
            data.put("health", this.gameEngine.entityManager.getPlayerHealth()-1);
            this.gameEngine.entityManager.updateEntity("updateHealth",this.gameEngine.entityManager.getPlayerEntityId(),data);
        }

        return true;
    }
    @Override
    public boolean keyUp(int keycode){
        if (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING")){
            keypressedlist.remove(Integer.toString(keycode));
        }

        return true;
    }

    public List<String> getKeypressedlist() {
        return keypressedlist;
    }
    public void resetKeypressedList(){
        this.keypressedlist= new ArrayList<String>();
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Handle the click event here
        if (this.gameEngine.sceneManager.getCurrentScene().equals("levelscene")){
            Vector3 temp = ((Scene)this.gameEngine.sceneManager.getScreen()).getCamera().unproject(new Vector3((float) screenX,(float) screenY,0));
            this.gameEngine.playerControlManager.shoot(new Vector2(temp.x,temp.y));
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return true;
    }

}
