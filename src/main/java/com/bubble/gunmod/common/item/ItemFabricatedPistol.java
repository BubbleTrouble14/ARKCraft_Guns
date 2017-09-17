package com.bubble.gunmod.common.item;

import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.common.item.attachment.AttachmentType;

public class ItemFabricatedPistol extends ItemRangedWeapon {

	public ItemFabricatedPistol(String name) {
		super(name, 350);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) {
		return item == RegisterItems.advanced_bullet;
	}

	@Override
	public int getReloadingTime() {
		return 60;
	}

	@Override
	public int getAmmunitionCapacity() {
		return 13;
	}

	@Override
	public boolean supportsAttachment(AttachmentType type) {
		return type == AttachmentType.SCOPE || type == AttachmentType.FLASH || type == AttachmentType.LASER
				|| type == AttachmentType.SILENCER;
	}

	@Override
	public ItemAmmunition getDefaultAmmunition() {
		return RegisterItems.advanced_bullet;
	}

	@Override
	public int getIntervalDuration() {
		return 6; //1
	}
}
