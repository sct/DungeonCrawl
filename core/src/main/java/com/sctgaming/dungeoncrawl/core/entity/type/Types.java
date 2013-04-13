package com.sctgaming.dungeoncrawl.core.entity.type;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.sctgaming.dungeoncrawl.core.entity.type.monster.Monsters;

public class Types {
	
	public static List<EntityType> MONSTER_TYPES = new ArrayList<EntityType>();
	public static List<WeaponType> WEAPON_TYPES = new ArrayList<WeaponType>();
	
	public static void initializeMonsters() {
		Field[] fields = Monsters.class.getFields();
		for (Field f : fields) {
			if (Modifier.isStatic(f.getModifiers())) {
				try {
					Object value = f.get(null);
					if (value instanceof EntityType) {
						registerMonster((EntityType) value);
					}
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				}
			}
		}
	}
	
	public static void registerWeapon(WeaponType weapon) {
		WEAPON_TYPES.add(weapon);
	}
	
	private static void registerMonster(EntityType monster) {
		
		MONSTER_TYPES.add(monster);
	}

}
