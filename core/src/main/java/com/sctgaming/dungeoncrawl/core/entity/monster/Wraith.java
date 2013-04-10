package com.sctgaming.dungeoncrawl.core.entity.monster;

import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;

public class Wraith extends Monster {

	public Wraith(TileMap map, int x, int y) {
		super(map, x, y);
		this.setMonsterTexture(EntityTextures.WRAITH);
	}

}
