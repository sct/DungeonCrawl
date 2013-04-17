package com.sctgaming.dungeoncrawl.core.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.ui.components.InventoryHolder;
import com.sctgaming.dungeoncrawl.core.ui.components.SideBar;
import com.sctgaming.dungeoncrawl.core.ui.components.TurnLog;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class GameOverlay implements Tickable {
	
	public static OrthographicCamera camera = new OrthographicCamera();;
	public static SpriteBatch batch = new SpriteBatch();
	
	public static OrthographicCamera fontCamera = new OrthographicCamera();
	public static SpriteBatch fontBatch = new SpriteBatch();
	public static BitmapFont font = new BitmapFont(Gdx.files.internal("04b03.fnt"), true);
	
	public static Container window = new Container();

    public static InventoryHolder inventoryHolder;
	
	public GameOverlay() {
		camera.setToOrtho(true, 1280, 720);
		fontCamera.setToOrtho(true);
		batch.setProjectionMatrix(camera.combined);
		fontBatch.setProjectionMatrix(fontCamera.combined);

        inventoryHolder = new InventoryHolder(GameScreen.player);
		
		window.addComponent(new SideBar());
		window.addComponent(new TurnLog());
        window.addComponent(inventoryHolder);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		batch.begin();
		TextureRegion sbg = new TextureRegion(Textures.INTERFACE);
		sbg.setRegion(0,0,215,360); 
		
		batch.draw(sbg,camera.viewportWidth-sbg.getRegionWidth(),0);
		batch.end();
		
		window.render(dt);
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
