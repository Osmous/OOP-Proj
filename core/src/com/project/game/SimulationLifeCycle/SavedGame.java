package com.project.game.SimulationLifeCycle;

import com.badlogic.gdx.Screen;
import com.project.game.Entity.Entity;
import com.project.game.Screen.Scene;

import java.util.ArrayList;
import java.util.List;

public class SavedGame {
    private List<Entity> loadedEntities;
    private Screen screen;

    public SavedGame(List<Entity> loadedEntities, Screen screen) {
        this.loadedEntities = loadedEntities;
        this.screen = screen;
    }

    public List<Entity> getLoadedEntities() {
        return loadedEntities;
    }

    public void setLoadedEntities(List<Entity> loadedEntities) {
        this.loadedEntities = new ArrayList<>(loadedEntities);
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
