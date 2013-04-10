package com.sctgaming.dungeoncrawl.core.utils;

public enum Directions {
	NORTH (0, -1),
	EAST (1, 0),
	SOUTH (0, 1),
	WEST (-1, 0);
	
	private final int x;
	private final int y;
	
	Directions(int x, int y) {
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
