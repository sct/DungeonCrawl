package com.sctgaming.dungeoncrawl.core.entity;

import com.badlogic.gdx.graphics.Color;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.entity.type.ItemType;
import com.sctgaming.dungeoncrawl.core.entity.type.Types;
import com.sctgaming.dungeoncrawl.core.tiles.Tile;
import com.sctgaming.dungeoncrawl.core.utils.Formulas;

import java.util.Random;

public class Item extends PropertyHolder implements Tickable {
	private static final long serialVersionUID = 6453773567723713592L;
	
	private ItemType type;
	private Tile tile;
	private boolean dropped = false;
    private boolean visible = false;
    private int turn = 0;
	
	public Item(ItemType type) {
		this.type = type;
	}

    public Item(int level) {
        this(getRandomType());
        getType().create();
        randomizeStats(level);
    }
	
	public ItemType getType() {
		return type;
	}
	
	public void setDropped(boolean dropped) {
        if (!dropped) {
            this.tile = null;
            GameScreen.map.removeItem(this);
        }
		this.dropped = dropped;
	}
	
	public boolean isDropped() {
		return dropped;
	}

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public Tile getTile() {
		return tile;
	}

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public static ItemType getRandomType() {
        Random rand = new Random();
        return Types.ITEM_TYPES.get(rand.nextInt(Types.ITEM_TYPES.size()));
    }

    public void randomizeStats(int level) {
        setProperty(Properties.STR, Formulas.getRandomStat(level));
        setProperty(Properties.AGI, Formulas.getRandomStat(level));
        setProperty(Properties.CON, Formulas.getRandomStat(level));
    }

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		if (isDropped() && tile != null) {
            Color clr = Color.WHITE.cpy();
            if (!isVisible()) {
                clr.r *= 0.3f;
                clr.g *= 0.3f;
                clr.b *= 0.3f;
            }
            GameScreen.BATCH.setColor(clr);
			GameScreen.BATCH.draw(getType().getTexture(), tile.getX() * 16, tile.getY() * 16 + 16, 16, -16);
            GameScreen.BATCH.setColor(1,1,1,1);
		}
		
	}

	@Override
	public void turn() {
        /* Set visible to false at the start of turn to make sure items out of sight are hidden */
		if (isVisible() && getTurn() != GameScreen.getTurn()) {
            setVisible(false);
        }
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
