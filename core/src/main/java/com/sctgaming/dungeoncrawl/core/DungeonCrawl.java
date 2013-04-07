package com.sctgaming.dungeoncrawl.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class DungeonCrawl extends Game {
	
	@Override
	public void create() {
		Gdx.graphics.setVSync(true);
		setScreen(new GameScreen());
	}
}
