package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.TextureBlender;
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
	
	@Override
	public TextureRegion getRenderTexture() {
		boolean north = getRelative(Directions.NORTH) instanceof Wall || getRelative(Directions.NORTH) instanceof Door;
		boolean south = getRelative(Directions.SOUTH) instanceof Wall || getRelative(Directions.SOUTH) instanceof Door;
		boolean east = getRelative(Directions.EAST) instanceof Wall || getRelative(Directions.EAST) instanceof Door;
		boolean west = getRelative(Directions.WEST) instanceof Wall || getRelative(Directions.WEST) instanceof Door;
		
		return TextureBlender.getRegion(getTexture(), north, south, east, west);
	}

}
