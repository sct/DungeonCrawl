package com.sctgaming.dungeoncrawl.core.entity.type;

import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class LivingType extends EntityType {
	private int maxHealth;

	public LivingType(String name, int maxHealth, boolean hostile) {
		super(name);
		this.maxHealth = maxHealth;
		setHostile(hostile);
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void setMonsterTexture(EntityTextures entity) {
		setTexture(Textures.MONSTERS, entity.getX(), entity.getY());
	}

}
