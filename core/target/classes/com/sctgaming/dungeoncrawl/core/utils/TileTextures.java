package com.sctgaming.dungeoncrawl.core.utils;

public enum TileTextures {
	FLOOR (0, 0),
	WALL (1, 0);
	
	private final int x;
	private final int y;
	
	TileTextures(int x, int y) {
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
