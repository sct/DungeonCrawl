package com.sctgaming.dungeoncrawl.core.utils;

public enum TileTextures {
	VOID (0,0),
	VOID2 (0,1),
	FLOOR (2, 4, 4, 4),
	CORRIDOR (2, 8, 4, 4),
	CAVE_FLOOR (1, 2),
	WALL (2, 0, 4, 4),
	CAVE (2, 1),
	DOOR (6, 0),
	DOOR_VERT (7, 0);
	
	private final int x;
	private final int y;
	private int w;
	private int h;
	
	TileTextures(int x, int y) {
		this(x, y, 1, 1);
		
	}
	
	TileTextures(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public int getX() {
		return this.x * 16;
	}
	
	public int getY() {
		return this.y * 16;
	}
	
    public int getWidth() {
    	return this.w * 16;
    }
    
    public int getHeight() {
    	return this.h * 16;
    }
}
