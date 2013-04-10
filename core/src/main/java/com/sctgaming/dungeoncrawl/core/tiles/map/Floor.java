package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Floor extends Tile {

	public Floor(TileMap map, int x, int y) {
		super(map, x, y, false);
		this.setTexture(TileTextures.RED_FLOOR);
	}
	
	@Override
	public boolean isFloor() {
		return true;
	}
}
