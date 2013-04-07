package com.sctgaming.dungeoncrawl.core.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.utils.Textures;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Tile {
	private int x;
	private int y;
	private boolean obstructed = false;
	private TextureRegion texture;
	private TileMap map;
	private boolean floor = false;
	private boolean door = false;
	private boolean cooridor = false;
	private boolean wall = false;
	
	public Tile(TileMap map, int x, int y, boolean obstructed) {
		this.x = x;
		this.y = y;
		this.obstructed = obstructed;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean getObstructed() {
		return this.obstructed;
	}
	
	public TextureRegion getTexture() {
		return this.texture;
	}
	
	public void setTexture(TileTextures tileText) {
		TextureRegion newText = new TextureRegion(Textures.TERRAIN);
		newText.setRegion(tileText.getX(), tileText.getY(), 16, 16);
		this.texture = newText;
	}
	
	public void setFloor(boolean floor) {
		this.floor = floor;
	}
	
	public boolean isFloor() {
		return this.floor;
	}
	
	public void setWall(boolean wall) {
		this.wall = floor;
	}
	
	public boolean isWall() {
		return this.wall;
	}
	
	public boolean isObstructed() {
		return this.obstructed;
	}
}
