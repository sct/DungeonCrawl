package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Door extends Tile {
	public Room room;
	private boolean connected = false;

	public Door(TileMap map, int x, int y, Room room) {
		super(map, x, y, false);
		this.setTexture(TileTextures.DOOR);
		this.room = room;
	}
	
	@Override
	public boolean isDoor() {
		return true;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public Room getRoom() {
		return room;
	}
	
	@Override
	public boolean canTunnel() {
		return true;
	}

}
