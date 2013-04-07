package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.map.Door;
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
	public static TileMap currentMap;
	public static Random rand = new Random();
	
	public static TileMap generateDungeon() {
		
		currentMap = new TileMap(DUNGEON_WIDTH, DUNGEON_HEIGHT);
		
		/*
		 * We know the width and height of the dungeon, so we can place the first room
		 * near the center.
		 */
		int firstRoomWidth = rand.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH;
		int firstRoomHeight = rand.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT;
		addRoom((DUNGEON_WIDTH/2)-(firstRoomWidth/2), (DUNGEON_HEIGHT/2)-(firstRoomHeight/2), firstRoomWidth, firstRoomHeight);
		
		return currentMap;
	}
	
	private static boolean addRoom(int startx, int starty, int w, int h) {
		
		/*
		 * First we check to make sure this room does not intersect with any
		 * other in-use tiles. If it does, return false and skip room creation.
		 */
		for (int x = startx; x < startx + w; x++) {
			for (int y = starty; y < starty + h; y++) {
				System.out.println("X: " + x + " Y: " + y);
				if (currentMap.getTile(x, y) != null && currentMap.getTile(x, y).isWall() == false) {
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
				currentMap.addTile(tile);
				finalx = x;
				finaly = y;
			}
		}
		
		// First we figure out how many doors we want. 1-3 depending on size
		int doorChance = rand.nextInt(100);
		int doorCount = 0;
		
		if (w == 3 && h == 3) {
			doorCount = 1;
		} else if (w < 6 && h < 6) {
			if (doorChance < 70) {
				doorCount = 1;
			} else if (doorChance < 90) {
				doorCount = 2;
			} else {
				doorCount = 3;
			}
		} else {
			if (doorChance < 40) {
				doorCount = 1;
			} else if (doorChance < 80) {
				doorCount = 2;
			} else {
				doorCount = 3;
			}
		}
		
		logAction("Door Chance","Door chance calculated result is " + doorCount);
		
		Set<Integer> doorSides = new HashSet<Integer>();
		doorSides.add(0);
		doorSides.add(1);
		
		boolean doorsComplete = false;
		int doorsAdded = 0;
		while (doorsComplete == false) {
			if (doorSides.isEmpty() || doorsAdded == doorCount) {
				doorsComplete = true;
				logAction("Doors Created","Door creation process completed");
			}
			
			int sideCheck = rand.nextInt(2);
			int doorSpot;
			switch (sideCheck) {
				case 0:
					if (doorSides.contains(sideCheck)) {
						doorSpot = startx + rand.nextInt(w);
						logAction("Door Attempt","Side: Top Offset: " + doorSpot);
						
						Door door = new Door(currentMap, doorSpot, starty - 1, false);
						door.setTexture(TileTextures.DOOR);
						currentMap.addTile(door);
						
						// Door added, now we close this side.
						doorSides.remove(sideCheck);
						doorsAdded += 1;
					}
					break;
				case 1:
					if (doorSides.contains(sideCheck)) {
						doorSpot = starty + rand.nextInt(h);
						logAction("Door Attempt","Side: Right Offset: " + doorSpot);
						
						Door door = new Door(currentMap, startx + w, doorSpot, false);
						door.setTexture(TileTextures.DOOR);
						currentMap.addTile(door);
						
						doorSides.remove(sideCheck);
						doorsAdded += 1;
					}
					break;
					
			}
		}
		
		/*
		 * Now the walls are created for the room. Walls will skip any occupied blocks.
		 */
		for (int x = startx - 1; x < ((startx - 1) + w + 2); x++) {
			for (int y = starty - 1; y < ((starty - 1) + h + 2); y++) {
				if (currentMap.getTile(x, y) == null) {
					tile = new Tile(currentMap,x,y,true);
					tile.setTexture(TileTextures.WALL);
					tile.setWall(true);
					logAction("Creating Wall", "Wall created @ X: " + x + " Y: " + y);
					currentMap.addTile(tile);
				}
			}
		}
		
		/*
		 * If it gets this far, the room creation was a success. Now we create a new Room
		 * object and pass over the tile data and entrance information, as well as point
		 * data.
		 */
		
		Room room = new Room();
		room.setLocation(startx, starty, finalx, finaly);
		currentMap.addRoom(room);
		logAction("Room Created", "Room sucessfully created. Size of " + w + "x" + h);
		
		return true;
	}
	
	private static void logAction(String action, String message) {
		if (DEBUG) {
			System.out.println("[" + action + "] " + message);
		}
	}
}
