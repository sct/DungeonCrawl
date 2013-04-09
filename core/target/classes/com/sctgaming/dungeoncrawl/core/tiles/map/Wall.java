package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Wall extends Tile {

	public Wall(TileMap map, int x, int y) {
		super(map, x, y, true);
		this.setTexture(TileTextures.WALL);
	}
	
	@Override
	public boolean isWall() {
		return true;
	}

}
