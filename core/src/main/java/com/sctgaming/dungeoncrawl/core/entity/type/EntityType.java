package com.sctgaming.dungeoncrawl.core.entity.type;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.entity.Property;
import com.sctgaming.dungeoncrawl.core.ui.components.TurnLog;
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
		float dodgeChance = Formulas.getDodgeChance(target.getProperty(Properties.AGI) + target.getType().getBonusStat(target, Properties.AGI), target.getProperty(Properties.LEVEL));
		float difference = (target.getProperty(Properties.LEVEL) - entity.getProperty(Properties.LEVEL))/100f;
		
		if (rand.nextFloat() > (dodgeChance + difference)) {
			target.takeDamage(calcDamage);
			TurnLog.addEntry(entity.toString() + " attacks " + target.toString() + " for " + calcDamage + " damage");
			System.out.println("[Combat] " + entity.toString() + " attacks " + target.toString() + " for " + calcDamage + " damage");
		} else {
			TurnLog.addEntry(entity.toString() + " MISSES attack on " + target.toString(),Color.RED);
			System.out.println("[Combat] " + entity.toString() + " MISSES attack on " + target.toString());
		}
		
	}
	
	public int getBonusStat(Entity entity, Property<Integer> key) {
		int stat = getBonusStatFromWeapons(entity, key);
		return stat;
	}
	
	public int getBonusStatFromWeapons(Entity entity, Property<Integer> key) {
		if (entity.getProperty(Properties.WEAPON) != null) {
			return entity.getProperty(Properties.WEAPON).getProperty(key);
		} else {
			return 0;
		}
	}
	
}
