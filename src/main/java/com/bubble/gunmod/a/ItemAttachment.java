package com.bubble.gunmod.a;

import com.bubble.gunmod.Main;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public abstract class ItemAttachment extends Item implements IAttachment {
	public ItemAttachment(ResourceLocation name) {
		super();
		setRegistryName(name);
		setCreativeTab(Main.tabGuns);
	}

}
