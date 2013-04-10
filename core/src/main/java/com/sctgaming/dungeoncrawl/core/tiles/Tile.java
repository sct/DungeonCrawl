package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.Textures;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public abstract class Tile implements Tickable {
	private final int x;
	private final int y;
	private final TileMap map;
	private boolean obstructed = false;
	private TextureRegion texture;
	private int cost = 1;
	private boolean usable = false;
	
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
	
	public void setObstructed(boolean obstructed) {
		this.obstructed = obstructed;
	}
	
	public boolean isObstructed() {
		return this.obstructed;
	}
	
	public TextureRegion getRenderTexture() {
		return this.texture;
	}
	
	public TextureRegion getTexture() {
		return this.texture;
	}
	
	public void setTexture(TileTextures tileText) {
		TextureRegion newText = new TextureRegion(Textures.TERRAIN);
		newText.setRegion(tileText.getX(), tileText.getY(), tileText.getWidth(), tileText.getHeight());
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
	
	public boolean isUsable() {
		return usable;
	}
	
	public void setUsable(boolean usable) {
		this.usable = usable;
	}
	
	public void use() {
		
	}
	
	public int getCost() {
		return cost;
	}
	
	public Entity getEntity() {
		for (Entity entity : GameScreen.entities) {
			if (this.getX() == entity.getX() && this.getY() == entity.getY()) {
				return entity;
			}
		}
		return null;
	}
	
	public Tile getRelative(Directions direction) {
		return getRelative(direction.getX(), direction.getY());
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
	
	@Override
	public void update(float dt) {
		
	}

	@Override
	public void render(float dt) {
		GameScreen.BATCH.draw(this.getRenderTexture(), this.getX(), this.getY() + 1, 1, -1);
	}

	@Override
	public void dispose() {
		
	}
}
