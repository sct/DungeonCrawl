package com.sctgaming.dungeoncrawl.core.entity.type;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Item;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.entity.Weapon;
import com.sctgaming.dungeoncrawl.core.player.Inventory;
import com.sctgaming.dungeoncrawl.core.ui.components.TurnLog;
import com.sctgaming.dungeoncrawl.core.utils.Formulas;

public class PlayerType extends LivingType implements Inventory {
	
	private List<Item> items = new ArrayList<Item>();

	public PlayerType() {
		super("Player",false);
	}
	
	@Override
	public void create(Entity entity) {
		entity.setProperty(Properties.CON, 8);
		entity.setProperty(Properties.WEAPON, new Weapon(Weapons.SHORT_SWORD, 1));
		super.create(entity);
	}
	
	public void addXP(Entity ent, int exp) {
		int oldExp = ent.getProperty(Properties.EXP);
		
		if (oldExp + exp >= Formulas.getTotalExp(ent.getProperty(Properties.LEVEL))) {
			int newExp = (oldExp + exp) - Formulas.getTotalExp(ent.getProperty(Properties.LEVEL));
			ent.setProperty(Properties.EXP, newExp);
			ent.getType().levelUp(ent);
		} else {
			ent.setProperty(Properties.EXP, oldExp + exp);
		}
	}
	
	public void levelUp(Entity ent) {
		int oldLevel = ent.getProperty(Properties.LEVEL);
		ent.setProperty(Properties.LEVEL, oldLevel + 1);
		
		int oldStr = ent.getProperty(Properties.STR);
		ent.setProperty(Properties.STR, oldStr + 1);
		
		int oldAgi = ent.getProperty(Properties.AGI);
		ent.setProperty(Properties.AGI, oldAgi + 1);
		
		int oldCon = ent.getProperty(Properties.CON);
		ent.setProperty(Properties.CON, oldCon + 1);
		
		ent.setProperty(Properties.HEALTH, Formulas.getHealth(oldCon + 1));
		
		TurnLog.addEntry("Congratulations! You have leveled up. You are now level " + ent.getProperty(Properties.LEVEL),Color.YELLOW);
		System.out.println("[Event] Player has leveled up! Player is now level " + ent.getProperty(Properties.LEVEL));
	}

	@Override
	public List<Item> getInventory() {
		return items;
	}

	@Override
	public void addItem(Item item) {
		items.add(item);
	}

	@Override
	public void removeItem(Item item) {
		items.remove(item);
	}

}
