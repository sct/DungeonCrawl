package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.TextureBlender;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Floor extends Tile {

	public Floor(TileMap map, int x, int y) {
		super(map, x, y, false);
		this.setTexture(TileTextures.FLOOR);
	}
	
	@Override
	public boolean isFloor() {
		return true;
	}
	
	@Override
	public TextureRegion getRenderTexture() {
		boolean north = getRelative(Directions.NORTH) instanceof Floor;
		boolean south = getRelative(Directions.SOUTH) instanceof Floor;
		boolean east = getRelative(Directions.EAST) instanceof Floor;
		boolean west = getRelative(Directions.WEST) instanceof Floor;
		
		return TextureBlender.getRegion(getTexture(), north, south, east, west);
	}
	
}
