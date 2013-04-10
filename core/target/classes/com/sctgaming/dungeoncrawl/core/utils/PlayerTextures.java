package com.sctgaming.dungeoncrawl.core.utils;

public enum PlayerTextures {
	WIZARD (0,0);
	
	private final int x;
	private final int y;
	
	PlayerTextures(int x, int y) {
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
