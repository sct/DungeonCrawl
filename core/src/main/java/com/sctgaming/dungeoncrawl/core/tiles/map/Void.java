package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Void extends Tile {
	private int cost = 5;

	public Void(TileMap map, int x, int y) {
		super(map, x, y, true);
		setTexture(TileTextures.VOID);
	}
	
	@Override
	public boolean isVoid() {
		return true;
	}
	
	@Override
	public boolean canTunnel() {
		return true;
	}

	@Override
	public int getCost() {
		return cost;
	}

}
