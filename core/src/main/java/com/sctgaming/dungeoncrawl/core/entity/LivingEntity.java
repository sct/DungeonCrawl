package com.sctgaming.dungeoncrawl.core.entity;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;

public class LivingEntity extends Entity {
	public int maxHealth = 10;
	public int health = 10;

	public LivingEntity(TileMap map, int x, int y) {
		super(map, x, y);
	}
	
	@Override
	public boolean setRelativePosition(int x, int y) {
		Tile tile = map.getTile(getX() + x, getY() + y);
		if (tile == null || tile.isObstructed() || tile.getEntity() != null) {
			return false;
		}
		return super.setRelativePosition(x, y);
	}

	@Override
	public void run(float time) {
		// TODO Auto-generated method stub
		
	}

}
