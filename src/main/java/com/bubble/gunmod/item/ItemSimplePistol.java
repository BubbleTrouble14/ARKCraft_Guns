package com.bubble.gunmod.item;

import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ItemAmmunition;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemSimplePistol extends ItemRangedWeapon {

	public ItemSimplePistol(String name) 
	{
		super(name);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) 
	{
		return item == RegisterItems.simple_bullet;
	}

	@Override
	public int getReloadingTime() 
	{
		return (int)(2.5 * 20);
	}

	@Override
	public int getAmmunitionCapacity()
	{
		return 6;
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
