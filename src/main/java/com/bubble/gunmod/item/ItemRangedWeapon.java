package com.bubble.gunmod.item;

import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ISupporting;

import net.minecraft.util.ResourceLocation;

public abstract class ItemRangedWeapon extends ItemRangedWeaponBase implements ISupporting {
	public ItemRangedWeapon(String name) {
		super(name);

		addPropertyOverride(new ResourceLocation("attachment"), (stack, world, entity) -> {
			AttachmentType at = ISupporting.getAttachmentType(stack);
			if (at == null)
				return 0;
			for (AttachmentType type : getSupportedAttachmentTypes()) {
				if (type == at) {
					return type.getId();
				}
			}
			return 0;
		});
	}
}
