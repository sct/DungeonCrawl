package com.sctgaming.dungeoncrawl.core.entity.type.monster;

import com.sctgaming.dungeoncrawl.core.entity.type.LivingType;
import com.sctgaming.dungeoncrawl.core.entity.type.Types;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;

public class Goblin extends LivingType {

	public Goblin() {
		super("Goblin",10,true);
		setMonsterTexture(EntityTextures.GOBLIN);
	}



}
