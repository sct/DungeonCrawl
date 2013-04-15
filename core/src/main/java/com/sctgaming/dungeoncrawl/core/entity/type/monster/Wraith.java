package com.sctgaming.dungeoncrawl.core.entity.type.monster;

import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.entity.type.LivingType;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;

public class Wraith extends LivingType {

	public Wraith() {
		super("Wraith",true);
		setMonsterTexture(EntityTextures.WRAITH);
	}
	
	@Override
	public void create(Entity entity) {
		entity.setProperty(Properties.EXP_REWARD, 15);
		entity.setProperty(Properties.CON, 2);
		super.create(entity);
	}


}
