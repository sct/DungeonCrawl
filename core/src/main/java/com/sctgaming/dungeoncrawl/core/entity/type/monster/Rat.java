package com.sctgaming.dungeoncrawl.core.entity.type.monster;

import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.entity.type.LivingType;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;

public class Rat extends LivingType {

	public Rat() {
		super("Rat", true);
		setMonsterTexture(EntityTextures.RAT);
	}
	
	@Override
	public void create(Entity entity) {
		entity.setProperty(Properties.EXP_REWARD, 35);
		entity.setProperty(Properties.CON, 1);
		super.create(entity);
	}

}
