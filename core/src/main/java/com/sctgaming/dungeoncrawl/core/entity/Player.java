package com.sctgaming.dungeoncrawl.core.entity;

import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.PlayerTextures;

public class Player extends LivingEntity {
	public boolean isMoving = false;
	public Directions direction = Directions.EAST;

	public Player(TileMap map, int x, int y) {
		super(map, x, y);
		this.setTexture(PlayerTextures.WIZARD);
	}
	
	@Override
	public boolean setRelativePosition(int x, int y) {
		if (super.setRelativePosition(x, y)) {
			GameScreen.CAMERA.translate(x,y);
			return true;
		}
		return false;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public Directions getDirection() {
		return direction;
	}
	
	public void setDirection(Directions direction) {
		this.direction = direction;
	}
	
	@Override
	public void run(float time) {
		if (isMoving()) {
			move(direction);
		}
	}

}
