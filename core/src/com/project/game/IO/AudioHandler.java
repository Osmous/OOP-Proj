package com.project.game.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.JsonValue;
import com.project.game.GameEngine;

import java.util.HashMap;
import java.util.Map;

public class AudioHandler {
    private GameEngine gameEngine;
    private Map<String, Sound> sounds;
    private Music backgroundMusic;
    protected AudioHandler(GameEngine gameEngine) {
        this.gameEngine= gameEngine;
        this.sounds = new HashMap<>();
        // set background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(this.gameEngine.config.get("sounds").getString("bgMusic")));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        // initalise all sound fx and store each sound into map
        for (JsonValue child = this.gameEngine.config.get("sounds").child(); child != null; child = child.next()) {
            sounds.put(child.name, Gdx.audio.newSound(Gdx.files.internal(child.asString())));
        }
    }

    protected void playSound(String sound){
        Sound gdxSound = sounds.get(sound);
        if (gdxSound !=null){
            gdxSound.play();
        }
    }
    protected void playBGM(){
        if(backgroundMusic.isPlaying()){
            backgroundMusic.stop();
        }else{
            backgroundMusic.play();
        }
    }

}
