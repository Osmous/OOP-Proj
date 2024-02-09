package com.project.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class GameEngine extends Game {
	private String gameConfigPath;
	public JsonValue config;

	public EntityManager entityManager;
	public AIControlManager aiControlManager;
	public PlayerControlManager playerControlManager;
	public IOManager ioManager;
	public CollisionManager collisionManager;
	public SimulationCycleManager simulationCycleManager;
	public SceneManager sceneManager;
	
	 public void loadConfig(){
		//load game specific configs.
		 JsonReader json = new JsonReader();
		 this.config = json.parse(Gdx.files.internal(this.gameConfigPath));

	 }
	@Override
	public void create() {
		// Init all managers
		entityManager = new EntityManager();
		aiControlManager = new AIControlManager();
		playerControlManager = new PlayerControlManager();
		ioManager = new IOManager();
		collisionManager = new CollisionManager();
		simulationCycleManager = new SimulationCycleManager();
		sceneManager = new SceneManager(this);
		gameConfigPath = "config.json";
		loadConfig();

		//set Main Screen as first Screen
		sceneManager.setMainScreen();
	};
	@Override
	public void render() {
		// this render function basically is main event loop.
		// techincally can just dump the io/player control function calls for movement here but
		// look into event based listeners and update xy position in the player control manager itself.
		// iomanager shd be using event listeners. not the if statements ah
		// https://libgdx.com/wiki/input/event-handling
		//
		// render scenes
		sceneManager.render();

	};
	@Override
	public void dispose() {
		// TODO
		if (screen != null) screen.hide();
		entityManager.clearAllEntities();
	};
}
