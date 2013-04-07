package com.sctgaming.dungeoncrawl.core;

/**
 * This interface represents any object in the game update and render loops.
 * First update is called, followed by render.
 * Delta time to tick are passed to the methods for keeping time.
 * 
 * @author Dalton
 * 
 */
public interface Tickable {

	/**
	 * Updates the game logic for this object.
	 * 
	 * @param dt delta time since last update operation in seconds
	 */
	public void update(float dt);

	/**
	 * Renders a frame of the game object.
	 * 
	 * @param dt delta time since last render operation in seconds
	 */
	public void render(float dt);

	/**
	 * Disposes any game or render objects on system exit.
	 */
	public void dispose();
}
