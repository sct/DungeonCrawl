package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.sctgaming.dungeoncrawl.core.tiles.TileMap;

public class Corridor extends Floor {

	public Corridor(TileMap map, int x, int y) {
		super(map, x, y);
	}
	
	@Override
	public boolean canTunnel() {
		return true;
	}
	
	@Override
	public boolean isCorridor() {
		return true;
	}

}
