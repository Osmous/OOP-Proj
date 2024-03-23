package com.project.game.IO;

import com.project.game.GameEngine;

import java.util.List;

public class IOManager {
    protected GameEngine gameEngine;
    protected InputHandler inputHandler;
    protected AudioHandler audioHandler;


    public IOManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.inputHandler = new InputHandler(this.gameEngine);
        this.audioHandler = new AudioHandler(this.gameEngine);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public AudioHandler getAudioHandler() {
        return audioHandler;
    }

    public List<String> getKeypressedlist() {
        return this.inputHandler.getKeypressedlist();
    }
    public void resetKeypressedList(){
        this.inputHandler.resetKeypressedList();
    }

    public void playSound(String sound){
        this.audioHandler.playSound(sound);
    }
    public void playBGM(){this.audioHandler.playBGM();}

}