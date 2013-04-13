package com.sctgaming.dungeoncrawl.core.entity;

import com.sctgaming.dungeoncrawl.core.entity.type.ItemType;

public class Item extends PropertyHolder {
	private ItemType type;
	
	public Item(ItemType type) {
		this.type = type;
	}
	
	public ItemType getType() {
		return type;
	}

}
