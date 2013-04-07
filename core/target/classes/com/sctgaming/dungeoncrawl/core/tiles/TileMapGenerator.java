package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.List;

import com.sctgaming.dungeoncrawl.core.utils.TileTextures;

/**
 * Generates a random dungeon based on a provided seed
 * 
 * @author sct
 *
 */
public class TileMapGenerator {
	public static final int MAX_ROOMS = 10;
	public static final int MAX_ROOM_WIDTH = 10;
	public static final int MAX_ROOM_HEIGHT = 10;
	public static final int MIN_ROOM_WIDTH = 3;
	public static final int MIN_ROOM_HEIGHT = 3;
	public static List<List<Tile>> tiles = new ArrayList<List<Tile>>();
	public static TileMap currentMap;
	
	public static List<List<Tile>> generateDungeon(TileMap map) {
		
		currentMap = map;
		populateColumns(15);
		
		addRoom(0,0,6,7);
		
		return tiles;
	}
	
	private static void populateColumns(int columns) {
		for (int x=0; x<columns; x++) {
			tiles.add(x, new ArrayList<Tile>(30));
		}
	}
	
	private static boolean addRoom(int startx, int starty, int w, int h) {
		
		for (int x = startx;x<w;x++) {
			for (int y = starty;y<h;y++) {
				System.out.println("X: " + x + " Y: " + y + " Array Size: " + tiles.get(x).size());
				if (tiles.get(x).size() < y || tiles.get(x).get(y) != null) {
					return false;
				}
			}
		}
		
		Tile tile;
		for (int x = startx;x<w;x++) {
			for (int y = starty;y<h;y++) {
				tile = new Tile(currentMap,x,y,false);
				tile.setTexture(TileTextures.FLOOR);
				tile.setFloor(true);
				tiles.get(x).add(y, tile);
			}
		}
		
		return true;
	}
}
