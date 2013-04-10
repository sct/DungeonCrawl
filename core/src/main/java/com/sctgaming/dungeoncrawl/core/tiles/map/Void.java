package com.sctgaming.dungeoncrawl.core.tiles.map;

import java.util.Random;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Void extends Tile {
	private int cost = 5;

	public Void(TileMap map, int x, int y) {
		super(map, x, y, true);
		setTexture(TileTextures.VOID);
		Random rand = new Random();
		
		int randPercent = rand.nextInt(100);
		
		if (randPercent < 50) {
			this.cost = 5;
		} else if (randPercent < 80) {
			this.cost = 15;
		} else {
			this.cost = 30;
		}
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
