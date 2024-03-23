Program flow as follows:

1. game engine create fucntion to initalise all required items
2. set screen to mainmenuscene
3. main screen play button change listener to switch to level scene
4. level scene reads level json, then show fucntion is called by libgdx as a init function
5. level scene show function then initalises all required game elements such as entities and stage for hud
6. level scene is also in charge of the enemy entity spawning mechanics
7. while game is running and current scene is level scene, Scenemanager checks for level change conditions every frame.
8. if conditions 0 enemies left to spawn and 0 enemies found in loaded entities list in entitymanager, then switch to end/ next level.
9. once current level count hits the end condition set in config.json, then Scenemanger ends game using simulation lifecycle maneger and changes scene to end scene (either win or lose)

note aicontrol, playercontrol, collision managers are only activated when current scene is set to level scene.

iomanager handles audio from the start and only handles user input at level scene. User input for buttons are handled using change listeners when creating the buttons under each scene respectively.
this is to utilise libgdx's library formats.