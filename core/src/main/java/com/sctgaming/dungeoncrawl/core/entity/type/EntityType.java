package com.sctgaming.dungeoncrawl.core.entity.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.entity.Entity;

public abstract class EntityType {
	private String name;
	public TextureRegion texture = null;
	private boolean hostile = false;
	
	public EntityType(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTexture(Texture texture, int x, int y) {
		TextureRegion newText = new TextureRegion(texture);
		newText.setRegion(x, y, 16, 16);
		this.texture = newText;
	}
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}
	
	public boolean isHostile() {
		return hostile;
	}
	
	public void setHostile(boolean hostile) {
		this.hostile = hostile;
	}
	
	public void attack(Entity entity, Entity target) {
		
	}
}
