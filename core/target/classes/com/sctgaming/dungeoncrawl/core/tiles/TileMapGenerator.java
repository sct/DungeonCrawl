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
	public static final boolean DEBUG = true;
	public static final int MAX_ROOMS = 10;
	public static final int MAX_ROOM_WIDTH = 10;
	public static final int MAX_ROOM_HEIGHT = 10;
	public static final int MIN_ROOM_WIDTH = 3;
	public static final int MIN_ROOM_HEIGHT = 3;
	public static int DUNGEON_WIDTH = 100;
	public static int DUNGEON_HEIGHT = 100;
	public static List<List<Tile>> tiles = new ArrayList<List<Tile>>();
	public static TileMap currentMap;
	
	public static List<List<Tile>> generateDungeon(TileMap map) {
		
		currentMap = map;
		populateColumns(DUNGEON_WIDTH, DUNGEON_HEIGHT);
		
		addRoom(0,1,1,6,7);
	    addRoom(1,8,1,4,4);
		
		return tiles;
	}
	
	/**
	 * Populates the multi-dimensional List with nulls to prevent
	 * out of bounds exception errors when checking for tiles
	 * 
	 * @param columns Number of columns to pre-populate
	 * @param rows Number of rows to pre-populate
	 */
	private static void populateColumns(int columns, int rows) {
		for (int x=0; x<columns; x++) {
			tiles.add(x, new ArrayList<Tile>(rows));
			for (int c=0; c < rows; c++) {
				tiles.get(x).add(c,null);
			}
		}
	}
	
	private static boolean addRoom(int room, int startx, int starty, int w, int h) {
		
		for (int x = startx; x < startx + w; x++) {
			for (int y = starty; y < starty + h; y++) {
				System.out.println("Room: " + room + " X: " + x + " Y: " + y);
				if (tiles.get(x).get(y) != null && tiles.get(x).get(y).isWall() == false) {
					System.out.println("Room: " + room + " Collision detected @ X: " + x + " Y: " + y);
					return false;
				}
			}
		}
		
		Tile tile;
		for (int x = startx; x < startx + w; x++) {
			for (int y = starty; y < starty + h; y++) {
				tile = new Tile(currentMap,x,y,false);
				tile.setTexture(TileTextures.FLOOR);
				tile.setFloor(true);
				logAction("Creating Floor", "Floor created @ X: " + x + " Y: " + y);
				tiles.get(x).add(y, tile);
			}
		}
		
		for (int x = startx - 1; x < ((startx - 1) + w + 2); x++) {
			for (int y = starty - 1; y < ((starty - 1) + h + 2); y++) {
				if (tiles.get(x).get(y) == null) {
					tile = new Tile(currentMap,x,y,true);
					tile.setTexture(TileTextures.WALL);
					tile.setWall(true);
					logAction("Creating Wall", "Room " + room + " Wall created @ X: " + x + " Y: " + y);
					tiles.get(x).add(y, tile);
				}
			}
		}
		
		return true;
	}
	
	private static void logAction(String action, String message) {
		if (DEBUG) {
			System.out.println("[" + action + "] " + message);
		}
	}
}
