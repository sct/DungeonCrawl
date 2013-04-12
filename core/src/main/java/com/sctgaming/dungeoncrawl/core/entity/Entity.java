package com.sctgaming.dungeoncrawl.core.entity;

import java.util.ArrayList;
import java.util.List;

import rlforj.los.IFovAlgorithm;
import rlforj.los.PrecisePermissive;

import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.entity.type.EntityType;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;

public abstract class Entity implements Tickable {
	private int x;
	private int y;
	private EntityType type;
	private Tile tile = null;
	private int viewRange = 0;
	private TileMap map;
	private float time = 0;
	private boolean moving = false;
	private Directions direction = Directions.EAST;
	public boolean animate = false;
	public boolean flip = false;
	private boolean visible = false;
	
	
	public Entity(EntityType type, TileMap map, int x, int y) {
		this.type = type;
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
	
	public TileMap getMap() {
		return map;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
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
	
	public void getFieldOfView() {
		IFovAlgorithm a = new PrecisePermissive();
		a.visitFieldOfView(map, getX(), getY(), getViewRange());
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
	
	
	
	public void animate() {
		if (animate) {
			type.texture.setRegion(type.texture.getRegionX() - 16, type.texture.getRegionY(), 16, 16);
			animate = false;
		} else {
			type.texture.setRegion(type.texture.getRegionX() + 16, type.texture.getRegionY(), 16, 16);
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
		if (isVisible()) {
			if (type.texture != null) {
				if (flip) {
					GameScreen.BATCH.draw(type.texture, this.getX() * 16 + 16, this.getY() * 16 + 16, -16, -16);
				} else {
					GameScreen.BATCH.draw(type.texture, this.getX() * 16, this.getY() * 16 + 16, 16, -16);
				}
			}
		}
		setVisible(false);
	}

	@Override
	public void dispose() {
		
	}
	
	@Override
	public String toString() {
		return type.getName();
	}
	
}
