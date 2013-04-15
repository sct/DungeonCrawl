package com.sctgaming.dungeoncrawl.core.entity;

import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.entity.type.ItemType;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;

public class Item extends PropertyHolder implements Tickable {
	private static final long serialVersionUID = 6453773567723713592L;
	
	private ItemType type;
	private Tile tile;
	private boolean dropped = false;
	
	public Item(ItemType type) {
		this.type = type;
	}
	
	public ItemType getType() {
		return type;
	}
	
	public void setDropped(boolean dropped) {
		this.dropped = dropped;
	}
	
	public boolean isDropped() {
		return dropped;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public Tile getTile() {
		return tile;
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		if (isDropped() && tile != null) {
			GameScreen.BATCH.draw(getType().getTexture(), tile.getX(), tile.getY(), 16, 16);
		}
		
	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
