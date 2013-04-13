package com.sctgaming.dungeoncrawl.core.utils;

public class Formulas {
	
	/**
	 * Returns quadratic health amount based on provided level
	 * 
	 * @param level
	 * @return int Health value
	 */
	public static int getHealth(int con) {
		return (int) Math.floor((6+(con*0.75)*(con*0.75)));
	}
	
	public static int getTotalExp(int level) {
		return (int) Math.floor(75+(level*1.85)*(level*1.85));
	}
}
