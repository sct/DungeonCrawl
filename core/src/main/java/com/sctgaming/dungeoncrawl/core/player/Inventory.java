package com.sctgaming.dungeoncrawl.core.player;

import java.util.List;

import com.sctgaming.dungeoncrawl.core.entity.Item;

/**
 * Basic inventoryHolder interface. Required methods for any object that
 * will hold an inventoryHolder of items.
 * 
 * @author sct
 *
 */
public interface Inventory {
	
	public List<Item> getInventory();
	
	public void addItem(Item item);
	
	public void removeItem(Item item);
}
