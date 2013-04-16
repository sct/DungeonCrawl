package com.sctgaming.dungeoncrawl.core.entity;

import java.util.Random;

import com.sctgaming.dungeoncrawl.core.entity.type.ItemType;
import com.sctgaming.dungeoncrawl.core.entity.type.Types;
import com.sctgaming.dungeoncrawl.core.entity.type.WeaponType;

public class Weapon extends Item {
	private static final long serialVersionUID = -8407267261022083492L;

	public Weapon(ItemType type, int level) {
		super(type);
		type.create();
		randomizeStats(level);
	}
	
	public Weapon(int level) {
		super(getRandomWeaponType());
		getType().create();
		randomizeStats(level);
	}
	
	public static WeaponType getRandomWeaponType() {
		Random rand = new Random();
		
		return Types.WEAPON_TYPES.get(rand.nextInt(Types.WEAPON_TYPES.size()));
	}
	

}
