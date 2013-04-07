package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sctgaming.dungeoncrawl.core.map.Room;
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
	public static List<Room> rooms = new ArrayList<Room>();
	public static TileMap currentMap;
	public static Random rand = new Random();
	
	public static List<List<Tile>> generateDungeon(TileMap map) {
		
		currentMap = map;
		populateColumns(DUNGEON_WIDTH, DUNGEON_HEIGHT);
		
		/*
		 * We know the width and height of the dungeon, so we can place the first room
		 * near the center.
		 */
		addRoom(1, 1, rand.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH, rand.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT);
		
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
	
	private static boolean addRoom(int startx, int starty, int w, int h) {
		
		/*
		 * First we check to make sure this room does not intersect with any
		 * other in-use tiles. If it does, return false and skip room creation.
		 */
		for (int x = startx; x < startx + w; x++) {
			for (int y = starty; y < starty + h; y++) {
				System.out.println("X: " + x + " Y: " + y);
				if (tiles.get(x).get(y) != null && tiles.get(x).get(y).isWall() == false) {
					System.out.println("Collision detected @ X: " + x + " Y: " + y);
					return false;
				}
			}
		}
		
		/*
		 * Now we create the floor tiles of the room and add the tiles to the main TileMap
		 */
		Tile tile;
		int finalx = 0;
		int finaly = 0;
		for (int x = startx; x < startx + w; x++) {
			for (int y = starty; y < starty + h; y++) {
				tile = new Tile(currentMap,x,y,false);
				tile.setTexture(TileTextures.FLOOR);
				tile.setFloor(true);
				logAction("Creating Floor", "Floor created @ X: " + x + " Y: " + y);
				tiles.get(x).add(y, tile);
				finalx = x;
				finaly = y;
			}
		}
		
		/*
		 * Now the walls are created for the room. Walls will skip any occupied blocks.
		 */
		for (int x = startx - 1; x < ((startx - 1) + w + 2); x++) {
			for (int y = starty - 1; y < ((starty - 1) + h + 2); y++) {
				if (tiles.get(x).get(y) == null) {
					tile = new Tile(currentMap,x,y,true);
					tile.setTexture(TileTextures.WALL);
					tile.setWall(true);
					logAction("Creating Wall", "Wall created @ X: " + x + " Y: " + y);
					tiles.get(x).add(y, tile);
				}
			}
		}
		
		// TODO: Create entrances randomly on the created room. Should probably happen before walls
		
		/*
		 * If it gets this far, the room creation was a success. Now we create a new Room
		 * object and pass over the tile data and entrance information, as well as point
		 * data.
		 */
		
		Room room = new Room();
		room.setLocation(startx, starty, finalx, finaly);
		rooms.add(room);
		logAction("Room Created", "Room sucessfully created. Size of " + w + "x" + h);
		
		return true;
	}
	
	private static void logAction(String action, String message) {
		if (DEBUG) {
			System.out.println("[" + action + "] " + message);
		}
	}
}
