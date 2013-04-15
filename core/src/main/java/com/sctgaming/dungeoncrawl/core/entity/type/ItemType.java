package com.sctgaming.dungeoncrawl.core.entity.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.utils.ItemTextures;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class ItemType {
	
	private String name;
	private String desc;
	private TextureRegion texture;
	private boolean equipable = false;

	public ItemType(String name, ItemTextures itemText) {
		this.name = name;
		setItemTexture(itemText);
	}
	
	public ItemType(String name) {
		this.name = name;
	}
	
	public ItemType(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public void create() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setEquipable(boolean equipable) {
		this.equipable = equipable;
	}
	
	public boolean isEquipable() {
		return equipable;
	}
	
	public void setItemTexture(ItemTextures it) {
		setTexture(Textures.ITEMS, it.getX(), it.getY());
	}
	
	public void setTexture(Texture texture, int x, int y) {
		TextureRegion newText = new TextureRegion(texture);
		newText.setRegion(x, y, 16, 16);
		this.texture = newText;
	}
	
	public TextureRegion getTexture() {
		return texture;
	}


}
