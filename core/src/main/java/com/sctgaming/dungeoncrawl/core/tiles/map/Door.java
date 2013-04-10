package com.sctgaming.dungeoncrawl.core.tiles.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

public class Door extends Tile {
	public Room room;
	private boolean connected = false;
	private boolean closed = true;

	public Door(TileMap map, int x, int y, Room room) {
		super(map, x, y, true);
		
		this.setTexture(TileTextures.DOOR);
		this.room = room;
		setUsable(true);
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
	
	public void setClosed(boolean closed) {
		this.closed = closed;
		this.setObstructed(closed);
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	public void use() {
		if (isClosed()) {
			getTexture().setRegion(getTexture().getRegionX(), getTexture().getRegionY() + 16, 16, 16);
		} else {
			getTexture().setRegion(getTexture().getRegionX(), getTexture().getRegionY() - 16, 16, 16);
		}
		setClosed(!isClosed());
	}
	
	@Override
	public TextureRegion getRenderTexture() {
		if (this.getRelative(Directions.NORTH) instanceof Wall && this.getRelative(Directions.SOUTH) instanceof Wall) {
			this.setTexture(TileTextures.DOOR_VERT);
		}
		return getTexture();
	}

}
