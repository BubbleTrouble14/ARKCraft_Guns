package com.bubble.gunmod.common.item;

import com.bubble.gunmod.Main;

import net.minecraft.item.Item;

public class ItemBase extends Item {
	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(Main.MODID + "." + name);
		setCreativeTab(Main.tabGuns);
		registerPropertyOverrides();
	}

	protected void registerPropertyOverrides() {
	}
}
