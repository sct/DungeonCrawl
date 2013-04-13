package com.sctgaming.dungeoncrawl.core.entity.type;

public class ItemType {
	
	private String name;
	private boolean equipable = false;

	public ItemType(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEquipable(boolean equipable) {
		this.equipable = equipable;
	}
	
	public boolean isEquipable() {
		return equipable;
	}


}
