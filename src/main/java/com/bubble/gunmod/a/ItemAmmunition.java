package com.bubble.gunmod.a;

import com.bubble.gunmod.Main;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public abstract class ItemAmmunition extends Item implements IAmmunition {
	public ItemAmmunition(ResourceLocation name) {
		this.setRegistryName(name);
		setCreativeTab(Main.tabGuns);
	}
}
