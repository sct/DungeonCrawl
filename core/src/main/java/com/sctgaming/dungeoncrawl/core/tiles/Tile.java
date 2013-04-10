package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.utils.Textures;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public abstract class Tile {
	private final int x;
	private final int y;
	private final TileMap map;
	private boolean obstructed = false;
	private TextureRegion texture;
	private int cost = 1;
	
	public Tile(TileMap map, int x, int y, boolean obstructed) {
		this.map = map;
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
	
	public boolean isVoid() {
		return false;
	}
	
	public boolean isFloor() {
		return false;
	}
	
	public boolean isWall() {
		return false;
	}
	
	public boolean isDoor() {
		return false;
	}
	
	public boolean isCorridor() {
		return false;
	}
	
	public boolean canTunnel() {
		return false;
	}
	
	public boolean isObstructed() {
		return this.obstructed;
	}
	
	public int getCost() {
		return cost;
	}
	
	public Tile getRelative(int x, int y) {
		Tile tile = map.getTile(getX() + x, getY() + y);
		return tile;
	}
	
	public List<Tile> getAdjacent() {
		List<Tile> adjacent = new ArrayList<Tile>();
		
		adjacent.add(getRelative(1, 0));
		adjacent.add(getRelative(-1, 0));
		adjacent.add(getRelative(0,1));
		adjacent.add(getRelative(0,-1));
		
		return adjacent;
	}
}
