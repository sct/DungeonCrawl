package com.sctgaming.dungeoncrawl.core.entity.monster;

import com.sctgaming.dungeoncrawl.core.entity.LivingEntity;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.utils.EntityTextures;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class Monster extends LivingEntity {

	public Monster(TileMap map, int x, int y) {
		super(map, x, y);
	}
	
	public void setMonsterTexture(EntityTextures entity) {
		this.setTexture(Textures.MONSTERS, entity.getX(), entity.getY());
	}
	
	@Override
	public void run(float time) {
		animate();
		super.run(time);
	}

}
