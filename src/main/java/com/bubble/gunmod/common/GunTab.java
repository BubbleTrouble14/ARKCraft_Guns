package com.bubble.gunmod.common;

import com.bubble.gunmod.RegisterItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GunTab extends CreativeTabs
{

	public GunTab(String label)
	{
		super(label);
	//	setBackgroundImageName("arkcraft.png");
	}

	@Override
	public boolean hasSearchBar()
	{
		return false;
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(RegisterItems.fabricated_pistol);
	}
	
	
}