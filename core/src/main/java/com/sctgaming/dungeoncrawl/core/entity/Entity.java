package com.sctgaming.dungeoncrawl.core.entity;

import java.util.ArrayList;
import java.util.List;

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
	private String name = null;
	private Tile tile = null;
	private int viewRange = 0;
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
	
	public void setViewRange(int viewRange) {
		this.viewRange = viewRange;
	}
	
	public int getViewRange() {
		return viewRange;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Tile> getViewTiles() {
		List<Tile> tiles = new ArrayList<Tile>();
		
		for (int y = getViewRange() * -1;y<=getViewRange();y++) {
			int xRange = getViewRange() - Math.abs(y);
			for (int x = xRange * -1;x<=xRange;x++) {
				if (!(x == 0 && y == 0)) {
					tiles.add(tile.getRelative(x,y));
				}
			}
		}
		
		return tiles;
	}
	
	public List<Entity> getViewEntities() {
		List<Entity> entities = new ArrayList<Entity>();
		
		for (int y = getViewRange() * -1;y<=getViewRange();y++) {
			int xRange = getViewRange() - Math.abs(y);
			for (int x = xRange * -1;x<=xRange;x++) {
				if (!(x == 0 && y == 0)) {
					for (Entity entity : GameScreen.entities) {
						if (entity.getX() == this.getX() + x && entity.getY() == this.getY() + y) {
							entities.add(entity);
						}
					}
				}
			}
		}
		
		return entities;
		
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
				GameScreen.BATCH.draw(texture, this.getX() * 16 + 16, this.getY() * 16 + 16, -16, -16);
			} else {
				GameScreen.BATCH.draw(texture, this.getX() * 16, this.getY() * 16 + 16, 16, -16);
			}
		}
	}

	@Override
	public void dispose() {
		
	}
	
}
