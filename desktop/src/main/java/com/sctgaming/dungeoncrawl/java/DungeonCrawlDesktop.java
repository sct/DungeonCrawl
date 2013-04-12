package com.sctgaming.dungeoncrawl.java;

import com.sctgaming.dungeoncrawl.core.DungeonCrawl;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DungeonCrawlDesktop {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dungeon Crawler";
		config.width = 1280;
		config.height = 720;
		config.useGL20 = true;
		new LwjglApplication(new DungeonCrawl(), config);
	}
}
