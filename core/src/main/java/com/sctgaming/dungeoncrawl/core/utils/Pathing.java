package com.sctgaming.dungeoncrawl.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.sctgaming.dungeoncrawl.core.tiles.PathNode;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;

public class Pathing {
	
	public static List<Tile> getPath(Tile start, Tile dest) {
		HashMap<Tile,PathNode> open = new HashMap<Tile,PathNode>();
		HashSet<Tile> closed = new HashSet<Tile>();
		PathNode curNode = null;
		
		open.put(start, new PathNode(null, start, dest));
		
		while (!open.containsKey(dest)) {
			curNode = null;
			
			if (open.isEmpty()) {
				return null;
			}
			
			for (PathNode node : open.values()) {
				if (curNode == null || node.getTotal() < curNode.getTotal()) {
					curNode = node;
				}
			}
			
			closed.add(curNode.getTile());
			open.remove(curNode.getTile());
			
			for (Tile tile : curNode.getTile().getAdjacent()) {
				if (tile != null && !closed.contains(tile) && !tile.isObstructed()) {
					if (!open.containsKey(tile) || (curNode.getCost() + tile.getCost()) < open.get(tile).getCost()) {
						PathNode newNode = new PathNode(curNode, tile, dest);
						open.put(tile, newNode);
					}
				}
			}
		}
		
		List<Tile> path = new ArrayList<Tile>();
		
		path.add(dest);
		
		while (curNode.getParent() != null) {
			path.add(curNode.getTile());
			curNode = curNode.getParent();
		}
		
		Collections.reverse(path);
		
		return path;
	}
}
