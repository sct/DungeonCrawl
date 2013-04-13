package com.sctgaming.dungeoncrawl.core.entity.type;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;
import com.sctgaming.dungeoncrawl.core.utils.Formulas;

public abstract class EntityType {
	private String name;
	public EntityTextures entTexture = null;
	public TextureRegion texture = null;
	private boolean hostile = false;
	
	public EntityType(String name) {
		this.name = name;
	}
	
	public abstract void create(Entity entity);
	
	public void addXP(Entity ent, int exp) {}
	
	public void levelUp(Entity ent) {}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTexture(Texture texture, int x, int y) {
		TextureRegion newText = new TextureRegion(texture);
		newText.setRegion(x, y, 16, 16);
		this.texture = newText;
	}
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}
	
	public boolean isHostile() {
		return hostile;
	}
	
	public void setHostile(boolean hostile) {
		this.hostile = hostile;
	}
	
	public void attack(Entity entity, Entity target) {
		int damageMin = 1;
		int damageMax = 1;
		
		
		Random rand = new Random();
		
		int calcDamage = (rand.nextInt(damageMax - damageMin + 1) + damageMin);
		float dodgeChance = Formulas.getDodgeChance(target.getProperty(Properties.AGI), target.getProperty(Properties.LEVEL));
		
		if (rand.nextFloat() > dodgeChance) {
			target.takeDamage(calcDamage);
			System.out.println("[Combat] " + entity.toString() + " attacks " + target.toString() + " for " + calcDamage + " damage");
		} else {
			System.out.println("[Combat] " + entity.toString() + " MISSES attack on " + target.toString());
		}
		
	}
	
}
