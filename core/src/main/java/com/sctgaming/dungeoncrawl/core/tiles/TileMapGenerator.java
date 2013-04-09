package com.sctgaming.dungeoncrawl.core.tiles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.tiles.map.Door;
import com.sctgaming.dungeoncrawl.core.tiles.map.Floor;
import com.sctgaming.dungeoncrawl.core.tiles.map.Room;
import com.sctgaming.dungeoncrawl.core.tiles.map.Wall;
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
	private static List<Point> unscannedTiles = new ArrayList<Point>();
	private static List<Point> scannedTiles = new ArrayList<Point>();
	
	public static TileMap generateDungeon() {
		
		currentMap = new TileMap(DUNGEON_WIDTH, DUNGEON_HEIGHT);
		
		/*
		 * We know the width and height of the dungeon, so we can place the first room
		 * near the center.
		 */
		addRoom(DUNGEON_WIDTH/2, DUNGEON_HEIGHT/2);
		
		// TODO: Add thing sto add more rooms on top of first
		
		createOtherRooms();
		
		doorPathTest();
		
		return currentMap;
	}
	
	private static void doorPathTest() {
		for (Room room : currentMap.getRooms()) {
			for (Door door : room.getDoors()) {
				for (Tile tile : door.getAdjacent()) {
					if (tile.isVoid()) {
						Floor floor = new Floor(currentMap, tile.getX(), tile.getY());
						currentMap.addTile(floor);
					}
				}
			}
		}
	}
	
	private static void createOtherRooms() {
		prepopulatePoints();
		
		Point randPoint;
		while (!unscannedTiles.isEmpty()) {
			randPoint = unscannedTiles.get(rand.nextInt(unscannedTiles.size()));
			
			Tile tile = currentMap.getTile(randPoint.x, randPoint.y);
			if (tile.isVoid()) {
				if (!addRoom(randPoint.x, randPoint.y)) {
					logAction("Room Creation", "Failed to fit room @ X: " + randPoint.x + " Y: " + randPoint.y);
				}
			}
			unscannedTiles.remove(randPoint);
			scannedTiles.add(randPoint);
		}
	}
	
	private static void prepopulatePoints() {
		for (int x = 0; x < DUNGEON_WIDTH; x++) {
			for (int y = 0; y < DUNGEON_HEIGHT; y++) {
				unscannedTiles.add(new Point(x,y));
			}
		}
	}
	
	private static boolean addRoom(int startx, int starty) {
		
		int w = rand.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH;
		int h = rand.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT;
		
		/*
		 * First we check to make sure this room does not intersect with any
		 * other in-use tiles. If it does, return false and skip room creation.
		 */
		/*for (int x = startx - 1; x < ((startx - 1) + w + 2); x++) {
			for (int y = starty - 1; y < ((starty - 1) + h + 2); y++) {
				if (x >= DUNGEON_WIDTH || x < 0 ||
					y >= DUNGEON_HEIGHT || y < 0 ||
					(currentMap.getTile(x, y) != null && currentMap.getTile(x, y).isWall() == false)) {
					logAction("Room Creation","Collision detected @ X: " + x + " Y: " + y);
					return false;
				}
			}
		}*/
		
		Room room = new Room(w,h);
		room.setLocation(startx, starty, startx + w, starty + h);
		
		for (Room cRoom : currentMap.getRooms()) {
			if (checkRoomCollision(room, cRoom))
				return false;
		}
		
		/*
		 * Now we create the floor tiles of the room and add the tiles to the main TileMap
		 */
		Floor floor;
		for (int x = startx; x < startx + w; x++) {
			for (int y = starty; y < starty + h; y++) {
				floor = new Floor(currentMap,x,y);
				logAction("Creating Floor", "Floor created @ X: " + x + " Y: " + y);
				currentMap.addTile(floor);
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
		doorSides.add(2);
		doorSides.add(3);
		
		boolean doorsComplete = false;
		int doorsAdded = 0;
		while (doorsComplete == false) {
			if (doorSides.isEmpty() || doorsAdded == doorCount) {
				doorsComplete = true;
				logAction("Doors Created","Door creation process completed");
			}
			
			int sideCheck = rand.nextInt(4);
			int doorSpot;
			switch (sideCheck) {
				case 0:
					if (doorSides.contains(sideCheck)) {
						doorSpot = startx + rand.nextInt(w);
						logAction("Door Attempt","Side: Top Offset: " + doorSpot);
						if (starty - 1 != 0) {
							Door door = new Door(currentMap, doorSpot, starty - 1, false);
							currentMap.addTile(door);
							room.addDoor(door);
						
							// Door added, now we close this side.
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
				case 1:
					if (doorSides.contains(sideCheck)) {
						doorSpot = starty + rand.nextInt(h);
						logAction("Door Attempt","Side: Right Offset: " + doorSpot);
						
						if (startx + w != DUNGEON_WIDTH) {
							Door door = new Door(currentMap, startx + w, doorSpot, false);
							currentMap.addTile(door);
							room.addDoor(door);
							
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
				case 2:
					if (doorSides.contains(sideCheck)) {
						doorSpot = startx + rand.nextInt(w);
						logAction("Door Attempt","Side: Bottom Offset: " + doorSpot);
						
						if (starty + h == DUNGEON_HEIGHT) {
							Door door = new Door(currentMap, doorSpot, starty + h, false);
							currentMap.addTile(door);
							room.addDoor(door);
							
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
				case 3:
					if (doorSides.contains(sideCheck)) {
						doorSpot = starty + rand.nextInt(h);
						logAction("Door Attempt","Side: Left Offset: " + doorSpot);
						if (startx - 1 != 0) {
							Door door = new Door(currentMap, startx - 1, doorSpot, false);
							currentMap.addTile(door);
							room.addDoor(door);
							
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
			}
		}
		
		/*
		 * Now the walls are created for the room. Walls will skip any occupied blocks.
		 */
		for (int x = startx - 1; x < ((startx - 1) + w + 2); x++) {
			for (int y = starty - 1; y < ((starty - 1) + h + 2); y++) {
				if (currentMap.getTile(x, y).isVoid()) {
					Wall  wall = new Wall(currentMap,x,y);
					logAction("Creating Wall", "Wall created @ X: " + x + " Y: " + y);
					currentMap.addTile(wall);
					room.addWall(wall);
				}
					
			}
		}
		
		/*
		 * If it gets this far, the room creation was a success. Now we create a new Room
		 * object and pass over the tile data and entrance information, as well as point
		 * data.
		 */
		
		currentMap.addRoom(room);
		logAction("Room Created", "Room sucessfully created. Size of " + w + "x" + h);
		
		return true;
	}
	
	/**
	 * Confirm a point is a valid location for door placement. Valid door placement
	 * requires at least two unobstructed paths available.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private static boolean checkDoorPlacement(int x, int y) {
		int obstructedCount = 0;
		
		for (int xs = -1; xs <= 1; xs++) {
			for (int ys = -1; ys <= 1; ys++) {
				if (xs != 0 || ys != 0) {
					logAction("Check Door","Checking door obstructions @ X: " + (x + xs) + " Y: " + (y + ys));
					int adjx = x + xs;
					int adjy = y + ys;
					if (adjx >= 0 && adjy >= 0) {
						Tile tile = currentMap.getTile(adjx, adjy);
						if (tile != null && tile.isObstructed()) {
							obstructedCount += 1;
						}
					}
				}
			}
		}
		
		logAction("Check Door","Obstructions counted @ " + obstructedCount);
		if (obstructedCount >= 3) {
			return false;
		}
		return true;
	}
	
	private static boolean checkRoomCollision(Room room1, Room room2) {
		if (room1.getMax().x + 2 >= DUNGEON_WIDTH || room1.getMin().x - 1 < 0) return true;
		if (room1.getMax().y + 2 >= DUNGEON_HEIGHT || room1.getMin().y - 1 < 0) return true;
		if (room1.getMax().x + 2 < room2.getMin().x) return false;
		if (room1.getMin().x - 2 > room2.getMax().x) return false;
		if (room1.getMax().y + 2 < room2.getMin().y) return false;
		if (room1.getMin().y - 2 > room2.getMax().y) return false;
		
		return true;
	}
	
	private static void logAction(String action, String message) {
		if (DEBUG) {
			System.out.println("[" + action + "] " + message);
		}
	}
}
