package com.sctgaming.dungeoncrawl.core.entity;

public class Properties {
	
	/**
	 * Level of an entity
	 * Default value of 1
	 */
	
	public static final Property<Integer> LEVEL = new Property<Integer>("level", Integer.class, 1);
	
	/**
	 * Current exp for level
	 * Default value of 0
	 */
	public static final Property<Integer> EXP = new Property<Integer>("exp", Integer.class, 0);
	
	/**
	 * Health of an entity
	 * Default value of 10
	 */
	public static final Property<Integer> HEALTH = new Property<Integer>("health", Integer.class, 20);
	
	/**
	 * Strength
	 * Default value of 5
	 */
	public static final Property<Integer> STR = new Property<Integer>("str", Integer.class, 5);
	
	/**
	 * Agility
	 * Default value of 5
	 */
	public static final Property<Integer> AGI = new Property<Integer>("agi", Integer.class, 5);
	
	/**
	 * Constitution
	 * Default value of 5
	 */
	public static final Property<Integer> CON = new Property<Integer>("con", Integer.class, 5);
	
	/**
	 * Amount of exp rewarded to player on death
	 * Default value of 1
	 */
	public static final Property<Integer> EXP_REWARD = new Property<Integer>("expReward", Integer.class, 1);
	
	/**
	 * Equipped Weapon
	 * Default value of null
	 */
	public static final Property<Weapon> WEAPON = new Property<Weapon>("weapon", Weapon.class);
}
