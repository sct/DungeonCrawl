package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.LivingEntity;
import com.sctgaming.dungeoncrawl.core.entity.type.Types;

public class TileMapSpawner {
	public static TileMap currentMap;
	public static List<Entity> monsters = new ArrayList<Entity>();
	
	public static void spawnMonsters(TileMap map) {
		currentMap = map;
		int maxMonsters = 50;
		int monsters = 0;
		
		Random rand = new Random();
		while (monsters < maxMonsters) {
			Tile tile = currentMap.getTile(rand.nextInt(currentMap.MAP_WIDTH), rand.nextInt(currentMap.MAP_HEIGHT));
			if (!tile.isObstructed() && tile.getEntity() == null) {
				LivingEntity entity = new LivingEntity(Types.MONSTER_TYPES.get(rand.nextInt(Types.MONSTER_TYPES.size())),currentMap,tile.getX(),tile.getY());
				map.addEntity(entity);
				monsters += 1;
				System.out.println("[MonsterSpawner] Monster spawned @ X: " + tile.getX() + " Y: " + tile.getY() + " of type " + entity.toString());
			}
		}
		
	}
	
}
