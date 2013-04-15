package com.sctgaming.dungeoncrawl.core.utils;

public enum WeaponTextures {
	SHORT_SWORD (0,0);
	
	private int x;
	private int y;
	
	WeaponTextures(int x, int y) {
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
