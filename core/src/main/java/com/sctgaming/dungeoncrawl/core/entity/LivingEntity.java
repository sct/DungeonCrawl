package com.sctgaming.dungeoncrawl.core.entity;

import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.entity.type.EntityType;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;

public class LivingEntity extends Entity {

	public LivingEntity(EntityType type, TileMap map, int x, int y) {
		super(type, map, x, y);
	}
	
	@Override
	public boolean setRelativePosition(int x, int y) {
		Tile tile = getMap().getTile(getX() + x, getY() + y);
		Entity ent = tile.getEntity();
		
		if (ent != null && (ent.equals(GameScreen.player) || this instanceof Player)) {
			getType().attack(this, ent);
			return false;
		}
		
		if (tile == null || tile.isObstructed()) {
			return false;
		}
		
		return super.setRelativePosition(x, y);
	}

	@Override
	public void run(float time) {
		animate();
	}

}
