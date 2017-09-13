package com.bubble.gunmod.item;

import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ItemAmmunition;
import com.bubble.gunmod.a.ItemRangedWeapon;

import net.minecraft.item.ItemStack;

public class ItemSimplePistol extends ItemRangedWeapon {

	public ItemSimplePistol(String name) {
		super(name);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getReloadingTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAmmunitionCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean supportsAttachment(AttachmentType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getAttachment(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttachment(ItemStack itemStack, ItemStack attachment) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initModels() {
		super.initModels();
	}

}
