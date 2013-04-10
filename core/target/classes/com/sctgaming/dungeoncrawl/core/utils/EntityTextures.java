package com.sctgaming.dungeoncrawl.core.utils;

public enum EntityTextures {
	GOBLIN (0,0),
	WRAITH (0,1);
	
	private final int x;
	private final int y;
	
	EntityTextures(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x  * 16;
	}
	
	public int getY() {
		return this.y * 16;
	}
}
