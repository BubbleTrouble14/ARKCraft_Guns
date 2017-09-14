package com.bubble.gunmod.item;

import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ItemAmmunition;

public class ItemShotgun extends ItemRangedWeapon {

	public ItemShotgun(String name) {
		super(name);
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
	public boolean supportsAttachment(AttachmentType type) {
		return false;
	}
}
