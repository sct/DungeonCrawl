package com.sctgaming.dungeoncrawl.html;

import com.sctgaming.dungeoncrawl.core.DungeonCrawl;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class DungeonCrawlHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new DungeonCrawl();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(1280, 720);
	}
}
