package com.sctgaming.dungeoncrawl.core.entity;

import java.util.Random;

import com.sctgaming.dungeoncrawl.core.entity.type.ItemType;
import com.sctgaming.dungeoncrawl.core.entity.type.Types;
import com.sctgaming.dungeoncrawl.core.entity.type.WeaponType;
import com.sctgaming.dungeoncrawl.core.utils.Formulas;

public class Weapon extends Item {
	private static final long serialVersionUID = -8407267261022083492L;

	public Weapon(ItemType type, int level) {
		super(type);
		type.create();
		randomizeStats(level);
	}
	
	public Weapon(int level) {
		super(getRandomType());
		getType().create();
		randomizeStats(level);
	}
	
	public static WeaponType getRandomType() {
		Random rand = new Random();
		
		return Types.WEAPON_TYPES.get(rand.nextInt(Types.WEAPON_TYPES.size()));
	}
	
	public void randomizeStats(int level) {
		setProperty(Properties.STR, Formulas.getRandomStat(level));
		setProperty(Properties.AGI, Formulas.getRandomStat(level));
		setProperty(Properties.CON, Formulas.getRandomStat(level));
	}
	

}
