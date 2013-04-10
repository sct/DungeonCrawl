package com.sctgaming.dungeoncrawl.core.entity;

import com.sctgaming.dungeoncrawl.core.tiles.TileMap;

public class LivingEntity extends Entity {
	public int maxHealth = 10;
	public int health = 10;

	public LivingEntity(TileMap map, int x, int y) {
		super(map, x, y);
	}
	
	@Override
	public boolean setRelativePosition(int x, int y) {
		if (map.getTile(getX() + x, getY() + y).isObstructed()) {
			return false;
		}
		return super.setRelativePosition(x, y);
	}

	@Override
	public void run(float time) {
		// TODO Auto-generated method stub
		
	}

}
