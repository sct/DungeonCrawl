package com.sctgaming.dungeoncrawl.core.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;

public abstract class Entity implements Tickable {
	private int x;
	private int y;
	private Tile tile = null;
	public TextureRegion texture = null;
	public TileMap map;
	public float time = 0;
	public boolean isMoving = false;
	public Directions direction = Directions.EAST;
	public boolean animate = false;
	public boolean flip = false;
	
	public Entity(TileMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
		this.tile = map.getTile(x, y);
	}
	
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void resetTime() {
		this.time = 0;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.tile = map.getTile(x, y);
	}
	
	public boolean setRelativePosition(int x, int y) {
		setPosition(this.x + x,this.y + y);
		return true;
	}
	
	public void move(Directions direction) {
		setRelativePosition(direction.getX(), direction.getY());
	}
	
	public Directions getDirection() {
		return direction;
	}
	
	public void setDirection(Directions direction) {
		this.direction = direction;
		if (direction.equals(Directions.WEST)) {
			flip = true;
		} else if (direction.equals(Directions.EAST)) {
			flip = false;
		}
	}
	
	public void setTexture(Texture texture, int x, int y) {
		TextureRegion newText = new TextureRegion(texture);
		newText.setRegion(x, y, 16, 16);
		this.texture = newText;
	}
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}
	
	public void animate() {
		if (animate) {
			texture.setRegion(texture.getRegionX() - 16, texture.getRegionY(), 16, 16);
			animate = false;
		} else {
			texture.setRegion(texture.getRegionX() + 16, texture.getRegionY(), 16, 16);
			animate = true;
		}
	}
	
	public abstract void run(float time);

	@Override
	public void update(float dt) {
		time += dt;
		if (time > 0.2F) {
			this.run(time);
			time = 0;
		}
	}

	@Override
	public void render(float dt) {
		if (texture != null) {
			if (flip) {
				GameScreen.BATCH.draw(texture, this.getX() + 1, this.getY() + 1, -1, -1);
			} else {
				GameScreen.BATCH.draw(texture, this.getX(), this.getY() + 1, 1, -1);
			}
		}
	}

	@Override
	public void dispose() {
		
	}
	
}
