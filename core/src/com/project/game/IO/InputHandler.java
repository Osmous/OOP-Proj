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

    //keypressed list needed as keyDown and keyUp are events that only fire once per user input.
    //needed to determine if key is continuously pressed down or not
    private List<String> keypressedlist;

    public InputHandler(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.keypressedlist = new ArrayList<String>();

    }
    @Override
    public boolean keyDown(int keycode) {
        if (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING")){
            // add keypress to list when keydown is triggered
            keypressedlist.add(Integer.toString(keycode));
        }
        if(keycode == com.badlogic.gdx.Input.Keys.ESCAPE && (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING") || this.gameEngine.simulationCycleManager.getCurrentState().equals("PAUSE") )){
            // check if escape is pressed to initiate pause screen
            // only allow if game is in running state or paused state
            gameEngine.simulationCycleManager.pauseGame();
        }

//        //FOR TESTING PURPOSE
//        if(keycode == com.badlogic.gdx.Input.Keys.P && this.gameEngine.sceneManager.getCurrentScene().equals("levelscene")){
//            List<Integer> holder = new ArrayList<Integer>();
//            for(Entity entity : this.gameEngine.entityManager.getLoadedEntity()){
//                if (entity.getType().equals("enemy")) {
//                    holder.add(entity.getEntityID());
//                }
//            }
//            for(Integer id : holder){
//                this.gameEngine.entityManager.deleteEntity(id);
//            }
//        }
//        //FOR TESTING PURPOSE
//        if(keycode == com.badlogic.gdx.Input.Keys.L && this.gameEngine.sceneManager.getCurrentScene().equals("levelscene")){
//            Map<String, Object> data = new HashMap<>();
//            data.put("health", this.gameEngine.entityManager.getPlayerHealth()-1);
//            this.gameEngine.entityManager.updateEntity("updateHealth",this.gameEngine.entityManager.getPlayerEntityId(),data);
//        }

        return true;
    }
    @Override
    public boolean keyUp(int keycode){
        if (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING")){
            // when key is no longer pressed remove key from list
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
            // translate screen to world coords
            this.gameEngine.playerControlManager.shoot(getWorldCoordinates(screenX,screenY));
        }
        return true;
    }
    private Vector2 getWorldCoordinates(int screenX, int screenY) {
        // function to translate click coords to scene coords
        // this is needed because coords origin start at bottom left corner for orthogonal camera
        // while screen coords start at top left corner
        Vector3 screenCoords = new Vector3(screenX, screenY, 0);
        Vector3 worldCoords = ((Scene)this.gameEngine.sceneManager.getScreen()).getCamera().unproject(screenCoords);
        return new Vector2(worldCoords.x, worldCoords.y);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

}
