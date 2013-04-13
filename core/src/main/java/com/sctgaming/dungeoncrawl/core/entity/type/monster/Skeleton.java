package com.sctgaming.dungeoncrawl.core.entity.type.monster;

import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.entity.type.LivingType;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;

public class Skeleton extends LivingType {

	public Skeleton() {
		super("Skeleton", true);
		setMonsterTexture(EntityTextures.SKELETON);
	}
	
	@Override
	public void create(Entity entity) {
		entity.setProperty(Properties.EXP_REWARD, 50);
		entity.setProperty(Properties.CON, 2);
		super.create(entity);
	}

}
