package com.sctgaming.dungeoncrawl.core.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureBlender {
	public TextureRegion texture;
	
	public static TextureRegion getRegion(TextureRegion texture, boolean north, boolean south, boolean east, boolean west) {
		int x = 0;
		int y = 0;
		
		if (west) {
			y += 2;
		}
		if (north) {
			y += 1;
		}
		if (east) {
			x += 2;
		}
		if (south) {
			x += 1;
		}
		TextureRegion region = new TextureRegion();
		region.setRegion(texture, x * 16, y * 16, 16, 16);
		return region;
	}

}
