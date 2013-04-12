package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.TextureBlender;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Corridor extends Floor {

	public Corridor(TileMap map, int x, int y) {
		super(map, x, y);
		super.setTexture(TileTextures.CORRIDOR);
	}
	
	@Override
	public boolean canTunnel() {
		return true;
	}
	
	@Override
	public boolean isCorridor() {
		return true;
	}
	
	@Override
	public TextureRegion getRenderTexture() {
		boolean north = getRelative(Directions.NORTH) instanceof Corridor || getRelative(Directions.NORTH) instanceof Door;
		boolean south = getRelative(Directions.SOUTH) instanceof Corridor || getRelative(Directions.SOUTH) instanceof Door;
		boolean east = getRelative(Directions.EAST) instanceof Corridor || getRelative(Directions.EAST) instanceof Door;
		boolean west = getRelative(Directions.WEST) instanceof Corridor || getRelative(Directions.WEST) instanceof Door;
		
		return TextureBlender.getRegion(getTexture(), north, south, east, west);
	}

}
