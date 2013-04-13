package com.sctgaming.dungeoncrawl.core.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.entity.type.EntityType;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.Formulas;

public class LivingEntity extends Entity {

	public LivingEntity(EntityType type, TileMap map, int x, int y) {
		super(type, map, x, y);
	}
	
	@Override
	public boolean setRelativePosition(int x, int y) {
		Tile tile = getMap().getTile(getX() + x, getY() + y);
		if (tile != null) {
			Entity ent = tile.getEntity();
		
			if (ent != null && (ent.equals(GameScreen.player) || this instanceof Player)) {
				getType().attack(this, ent);
				return false;
			}
		}
		
		if (tile == null || tile.isObstructed()) {
			return false;
		}
		
		return super.setRelativePosition(x, y);
	}

	@Override
	public void run(float time) {
		animate();
	}
	
	public void renderHealth() {
		if (isVisible()) {
			GameScreen.SHAPE.begin(ShapeType.Filled);
			GameScreen.SHAPE.setColor(Color.RED);
			GameScreen.SHAPE.rect(getX() * 16, getY() * 16 + 15, 16, 1);
			double hpPercent = this.getProperty(Properties.HEALTH) / (double) Formulas.getHealth(this.getProperty(Properties.CON));
			int pixPercent = (int) Math.floor(hpPercent * 16);
			GameScreen.SHAPE.setColor(Color.GREEN);
			GameScreen.SHAPE.rect(getX() * 16, getY() * 16 + 15, pixPercent, 1);
			GameScreen.SHAPE.end();
		}
	}

}
