package com.sctgaming.dungeoncrawl.core.entity.type;

import com.sctgaming.dungeoncrawl.core.utils.Textures;
import com.sctgaming.dungeoncrawl.core.utils.WeaponTextures;

public class WeaponType extends ItemType {
	
	public WeaponType(String name, WeaponTextures weaponTexture) {
		super(name);
		setEquipable(true);
		setWeaponTexture(weaponTexture);
	}
	
	@Override
	public void create() {
		
	}
	
	public void setWeaponTexture(WeaponTextures weaponTexture) {
		this.setTexture(Textures.WEAPONS, weaponTexture.getX(), weaponTexture.getY());
	}


}
