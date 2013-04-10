package com.sctgaming.dungeoncrawl.core.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.PlayerTextures;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public abstract class Entity implements Tickable {
	public int x;
	public int y;
	public TextureRegion texture = null;
	public TileMap map;
	public float time = 0;
	
	public Entity(TileMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean setRelativePosition(int x, int y) {
		this.x += x;
		this.y += y;
		return true;
	}
	
	public void move(Directions direction) {
		setRelativePosition(direction.getX(), direction.getY());
	}
	
	public TextureRegion getTexture() {
		return texture;
	}
	
	public void setTexture(PlayerTextures playerText) {
		TextureRegion newText = new TextureRegion(Textures.WIZARD);
		newText.setRegion(playerText.getX(), playerText.getY(), 16, 16);
		this.texture = newText;
	}
	
	public abstract void run(float time);

	@Override
	public void update(float dt) {
		time += dt;
		if (time > 0.20F) {
			this.run(time);
			time = 0;
		}
	}

	@Override
	public void render(float dt) {
		GameScreen.BATCH.begin();
		if (texture != null) {
			GameScreen.BATCH.draw(texture, this.getX(), this.getY() + 1, 1, -1);
		}
		GameScreen.BATCH.end();
	}

	@Override
	public void dispose() {
		
	}
	
}
