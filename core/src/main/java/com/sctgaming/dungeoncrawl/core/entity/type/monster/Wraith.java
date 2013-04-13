package com.sctgaming.dungeoncrawl.core.entity.type.monster;

import com.sctgaming.dungeoncrawl.core.entity.type.LivingType;
import com.sctgaming.dungeoncrawl.core.entity.type.Types;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;

public class Wraith extends LivingType {

	public Wraith() {
		super("Wraith",true);
		setMonsterTexture(EntityTextures.WRAITH);
	}


}
