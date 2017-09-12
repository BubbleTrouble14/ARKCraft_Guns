package com.bubble.gunmod.item.attachment;

import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ItemAttachment;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemFlashlight extends ItemAttachment{

	public ItemFlashlight(ResourceLocation name) {
		super(name);
	}

	@Override
	public void runEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runUseEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runClientSideEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runClientSideUseEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttachmentType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
