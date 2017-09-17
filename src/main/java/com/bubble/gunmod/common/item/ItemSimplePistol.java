package com.bubble.gunmod.common.item;

import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.common.item.attachment.AttachmentType;

public class ItemSimplePistol extends ItemRangedWeapon {

	public ItemSimplePistol(String name) {
		super(name, 150);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) {
		return item == RegisterItems.simple_bullet;
	}

	@Override
	public int getReloadingTime() {
		return (int) (2.5 * 20);
	}

	@Override
	public int getAmmunitionCapacity() {
		return 6;
	}

	@Override
	public boolean supportsAttachment(AttachmentType type) {
		return type == AttachmentType.SCOPE || type == AttachmentType.FLASH || type == AttachmentType.LASER
				|| type == AttachmentType.SILENCER;
	}

	@Override
	public ItemAmmunition getDefaultAmmunition() {
		return RegisterItems.simple_bullet;
	}

	@Override
	public int getIntervalDuration() {
		return 6;// 1.5s
	}
}
