package com.sctgaming.dungeoncrawl.core.entity;

import com.badlogic.gdx.graphics.Color;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.entity.type.EntityType;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.ui.components.TurnLog;
import com.sctgaming.dungeoncrawl.core.utils.Directions;
import com.sctgaming.dungeoncrawl.core.utils.PlayerTextures;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

import java.util.List;

public class Player extends LivingEntity {
	private static final long serialVersionUID = 6079509310535264443L;

	public Player(EntityType type, TileMap map, int x, int y) {
		super(type, map, x, y);
		type.setTexture(Textures.PLAYER,PlayerTextures.WIZARD.getX(),PlayerTextures.WIZARD.getY());
		setViewRange(6);
		setVisible(true);
		getFieldOfView();
	}
	
	@Override
	public void setPosition(int x, int y) {
		GameScreen.CAMERA.translate((x - getX()) * 16,(y - getY()) * 16);
		GameScreen.CAMERA.update();
		super.setPosition(x, y);
	}
	
	
	public void setMoving(boolean isMoving) {
		if (isMoving) {
			move(getDirection());
			resetTime();
		}
		super.setMoving(isMoving);
	}
	
	public void action() {
		for (Tile adjacent : getTile().getAdjacent()) {
			if (adjacent != null && adjacent.isUsable()) {
				adjacent.use();
			}
		}
		GameScreen.incrementTurn();
	}
	
	@Override
	public void move(Directions direction) {
		super.move(direction);
        List<Item> items = getTile().getItems();
        if (!items.isEmpty()) {
            if (items.size() == 1) {
                TurnLog.addEntry("There is a " + items.get(0).getType().getName() + " here. Press X to pick it up.", Color.CYAN);
            } else {
                String droppedItems = "";
                int x = 0;
                for (Item item : items) {
                    if (x != 0) {
                        droppedItems += ", ";
                    }
                    droppedItems += item.getType().getName();
                }
                TurnLog.addEntry("There are several items here [" + droppedItems + "]. Press X to pick up items.", Color.CYAN);
            }
        }
		GameScreen.incrementTurn();
	}
	
	@Override
	public void run(float time) {
		animate();
		if (isMoving()) {
			move(getDirection());
		}
		
	}
	
	@Override
	public void turn() {
		getFieldOfView();
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
	}

}
