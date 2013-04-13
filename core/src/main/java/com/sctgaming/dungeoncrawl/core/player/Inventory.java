package com.sctgaming.dungeoncrawl.core.player;

import java.util.List;

import com.sctgaming.dungeoncrawl.core.entity.Item;

/**
 * Basic inventory interface. Required methods for any object that
 * will hold an inventory of items.
 * 
 * @author sct
 *
 */
public interface Inventory {
	
	public List<Item> getInventory();
	
	public void addItem(Item item);
	
	public void removeItem(Item item);
}
