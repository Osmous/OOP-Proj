package com.project.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.project.game.Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOManager extends InputAdapter {
    private GameEngine gameEngine;
    private List<String> keypressedlist;
    private boolean isButtonPressed;


    public IOManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.keypressedlist = new ArrayList<String>();
        this.isButtonPressed = false;

    }

    @Override
    public boolean keyDown(int keycode) {
//        if (keycode == Input.Keys.LEFT) {
//            //gameEngine.playerControlManager.movePlayerLeft();
//        } else if (keycode == Input.Keys.RIGHT) {
//            //gameEngine.playerControlManager.movePlayerRight();
//        }
        if (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING")){
            keypressedlist.add(Integer.toString(keycode));
//            System.out.println(keycode);
        }
        if(keycode == Input.Keys.ESCAPE && (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING") || this.gameEngine.simulationCycleManager.getCurrentState().equals("PAUSE") )){
            gameEngine.simulationCycleManager.pauseGame();
        }

        //FOR TESTING PURPOSE
        if(keycode == Input.Keys.P && this.gameEngine.sceneManager.getCurrentScene().equals("levelscene")){
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
        if(keycode == Input.Keys.L && this.gameEngine.sceneManager.getCurrentScene().equals("levelscene")){
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
        if (!isButtonPressed) {
            isButtonPressed = true; // Set the button pressed flag to true
            // Handle the click event here
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isButtonPressed) {
            isButtonPressed = false; // Set the button pressed flag to false
        }
        return true;
    }

    public void reset(){
        this.keypressedlist = new ArrayList<String>();
        this.isButtonPressed = false;
    }

}