package com.sctgaming.dungeoncrawl.core.entity.type;

import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;
import com.sctgaming.dungeoncrawl.core.utils.Formulas;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class LivingType extends EntityType {

	public LivingType(String name, boolean hostile) {
		super(name);
		setHostile(hostile);
	}
	
	public void create(Entity entity) {
		entity.setProperty(Properties.HEALTH, Formulas.getHealth(entity.getProperty(Properties.CON)));
	}
	
	public int getMaxHealth(Entity ent) {
		return Formulas.getHealth(ent.getProperty(Properties.CON));
	}
	
	public void setMonsterTexture(EntityTextures entity) {
		setTexture(Textures.MONSTERS, entity.getX(), entity.getY());
	}

}
