package com.bubble.gunmod.a;

import com.bubble.gunmod.Main;

import net.minecraft.item.Item;

public abstract class ItemAmmunition extends Item implements IAmmunition {
	public ItemAmmunition(String name) {
		this.setRegistryName(name);
		setUnlocalizedName(Main.MODID + "." + name);
		setCreativeTab(Main.tabGuns);
	}
}
