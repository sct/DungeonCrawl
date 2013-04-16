package com.sctgaming.dungeoncrawl.core.utils;

import java.util.Random;

public class Formulas {
	
	/**
	 * Returns quadratic health amount based on provided constitution
	 * 
	 * @param con
	 * @return int Health value
	 */
	public static int getHealth(int con) {
		return (int) Math.floor((6+(con*0.75)*(con*0.75)));
	}
	
	/**
	 * Returns quadratic amounts for total xp required per level
	 * 
	 * @param level
	 * @return int Total EXP
	 */
	public static int getTotalExp(int level) {
		return (int) Math.floor(75+(level*1.85)*(level*1.85));
	}
	
	/**
	 * Returns a calculated dodge chance based on provided agility value
	 * 
	 * @param agi
	 * @param level
	 * @return float Dodge chance
	 */
	public static float getDodgeChance(int agi, int level) {
		return (float) (1 - (0.7 *(1 - (agi * 0.2)/100)));
	}

    /**
     * Returns a random stat value based on the provided level
     *
     * @param level
     * @return
     */
	public static int getRandomStat(int level) {
		Random rand = new Random();
		
		int chance = rand.nextInt(100);
		float multiplier = 0f;
		
		if (chance < 20) {
			// No stat bonus
			return 0;
		} else if (chance < 70) {
			multiplier = 0.15f;
		} else if (chance < 90) {
			multiplier = 0.25f;
		} else {
			multiplier = 0.30f;
		}
		
		return (int) Math.max(1,level*multiplier);
	}
}
