package com.bubble.gunmod.common.item.attachment;

import com.bubble.gunmod.Main;

import net.minecraft.item.Item;

public abstract class ItemAttachment extends Item implements IAttachment {
	public ItemAttachment(String name) {
		super();
		setRegistryName(name);
		setUnlocalizedName(Main.MODID + "." + name);
		setCreativeTab(Main.tabGuns);
	}

}
