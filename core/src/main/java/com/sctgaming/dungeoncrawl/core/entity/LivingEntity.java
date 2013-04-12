package com.sctgaming.dungeoncrawl.core.entity;

import rlforj.los.ILosAlgorithm;
import rlforj.los.ShadowCasting;

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
		if (tile == null || tile.isObstructed() || tile.getEntity() != null) {
			return false;
		}
		return super.setRelativePosition(x, y);
	}

	@Override
	public void run(float time) {
		animate();
		ILosAlgorithm a = new ShadowCasting();
//		System.out.println(a);
		if (a.existsLineOfSight(getMap(), GameScreen.player.getX(), GameScreen.player.getY(), getX(), getY(), false)) {
			System.out.println("[Sighted] " + this.toString() + " can see the player!");
		}
	}

}
