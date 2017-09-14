package com.bubble.gunmod.item;

import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ItemAmmunition;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemLongneckRifle extends ItemRangedWeapon{

	public ItemLongneckRifle(String name) 
	{
		super(name);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) 
	{
		return item == RegisterItems.simple_rifle_ammo;
	}

	@Override
	public int getReloadingTime()
	{
		return 80;
	}

	@Override
	public int getAmmunitionCapacity()
	{
		return 1;
	}

	@Override
	public boolean supportsAttachment(AttachmentType type) 
	{
		return type == AttachmentType.SCOPE ||  type == AttachmentType.FLASH ||  type == AttachmentType.LASER ||  type == AttachmentType.SILENCER;
	}
}
