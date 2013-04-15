package com.sctgaming.dungeoncrawl.core.ui.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.ui.Component;
import com.sctgaming.dungeoncrawl.core.utils.Formulas;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class SideBar extends Component {

	@Override
	public void render(float dt) {
		getBatch().begin();
		TextureRegion sbg = new TextureRegion(Textures.INTERFACE);
		sbg.setRegion(0,0,215,360); 
		
		getBatch().draw(sbg,getCamera().viewportWidth-sbg.getRegionWidth(),0);
		
		float sidePosX = getCamera().viewportWidth - 215 + 20;
		getFont().draw(getBatch(), "Dungeon Crawler v0.1", sidePosX, 20);
		getFont().draw(getBatch(), "Turn: " + GameScreen.getTurn(), sidePosX, 40);
		getFont().draw(getBatch(), "Level: " + GameScreen.player.getProperty(Properties.LEVEL), sidePosX, 60);
		getFont().draw(getBatch(), "Health: " + GameScreen.player.getProperty(Properties.HEALTH) + "/" + Formulas.getHealth(GameScreen.player.getProperty(Properties.CON)), sidePosX, 80);
		getFont().draw(getBatch(), "EXP: " + GameScreen.player.getProperty(Properties.EXP) + "/" + Formulas.getTotalExp(GameScreen.player.getProperty(Properties.LEVEL)), sidePosX, 100);
		getFont().draw(getBatch(), "STR: " + GameScreen.player.getProperty(Properties.STR), sidePosX, 120);
		getFont().draw(getBatch(), "AGI: " + GameScreen.player.getProperty(Properties.AGI) + " + " + GameScreen.player.getType().getBonusStat(GameScreen.player, Properties.AGI), sidePosX, 140);
		getFont().draw(getBatch(), "CON: " + GameScreen.player.getProperty(Properties.CON), sidePosX, 160);
		getFont().draw(getBatch(), "Weapon: " + GameScreen.player.getProperty(Properties.WEAPON).getType().getName(), sidePosX, 180);
		getBatch().end();
	}
}
