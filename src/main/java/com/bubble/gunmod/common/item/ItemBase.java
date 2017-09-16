package com.bubble.gunmod.common.item;

import java.util.HashSet;
import java.util.Set;

import com.bubble.gunmod.Main;

import net.minecraft.item.Item;

public class ItemBase extends Item
{
	public static Set<Item> itemList = new HashSet<>();
	
	public ItemBase(String name)
	{
		itemList.add(this);
		setRegistryName(name);
		setUnlocalizedName(Main.MODID + "." + name);
		setCreativeTab(Main.tabGuns);
	}
	
	public static Set<Item> getItemList()
	{
		return itemList;
	}
}
