package com.bubble.gunmod.item;

import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ItemAmmunition;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemFabricatedPistol extends ItemRangedWeapon
{

	public ItemFabricatedPistol(String name) 
	{
		super(name);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) 
	{
		return item == RegisterItems.advanced_bullet;
	}

	@Override
	public int getReloadingTime() 
	{
		return 60;
	}

	@Override
	public int getAmmunitionCapacity() 
	{
		return 13;
	}

	@Override
	public boolean supportsAttachment(AttachmentType type) 
	{
		return type == AttachmentType.SCOPE ||  type == AttachmentType.FLASH ||  type == AttachmentType.LASER ||  type == AttachmentType.SILENCER;
	}

	@Override
	public ItemStack getAttachment(ItemStack itemStack) 
	{
		return itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey("attachment")
				? new ItemStack(itemStack.getTagCompound().getCompoundTag("attachment"))
				: ItemStack.EMPTY;
	}

	@Override
	public void setAttachment(ItemStack itemStack, ItemStack attachment) 
	{
		if (itemStack.getTagCompound() == null)
			itemStack.setTagCompound(new NBTTagCompound());

		NBTTagCompound c = itemStack.getTagCompound();
		c.setTag("attachment", attachment.serializeNBT());		
	}

}
