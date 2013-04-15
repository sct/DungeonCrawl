package com.sctgaming.dungeoncrawl.core.utils;

public enum ItemTextures {
	CORPSE (0,0);
	
	private int x;
	private int y;
	
	ItemTextures(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
