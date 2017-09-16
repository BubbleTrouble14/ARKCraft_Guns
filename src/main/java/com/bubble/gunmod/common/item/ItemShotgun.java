package com.bubble.gunmod.common.item;

import com.bubble.gunmod.RegisterItems;

public class ItemShotgun extends ItemRangedWeaponBase {

	public ItemShotgun(String name) {
		super(name, 750);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) {
		return item == RegisterItems.simple_shotgun_ammo;
	}

	@Override
	public int getReloadingTime() {
		return 60;
	}

	@Override
	public int getAmmunitionCapacity() {
		return 2;
	}

	@Override
	public ItemAmmunition getDefaultAmmunition() {
		return RegisterItems.simple_shotgun_ammo;
	}

	@Override
	public int getIntervalDuration() {
		return 10; // 2s	
	}
}
