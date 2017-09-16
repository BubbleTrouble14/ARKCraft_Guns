package com.bubble.gunmod.common.item.attachment;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemScope extends ItemAttachment{

	public ItemScope(String name) {
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
	public AttachmentType getType() 
	{
		return AttachmentType.SCOPE;
	}

}
