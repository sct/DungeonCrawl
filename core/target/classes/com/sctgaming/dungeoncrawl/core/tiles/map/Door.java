package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Door extends Tile {

	public Door(TileMap map, int x, int y, boolean obstructed) {
		super(map, x, y, obstructed);
		this.setTexture(TileTextures.DOOR);
	}
	
	@Override
	public boolean isDoor() {
		return true;
	}

}
