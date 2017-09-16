package com.bubble.gunmod.common.item.attachment;

import com.bubble.gunmod.RegisterBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemFlashlight extends ItemAttachment{

	public ItemFlashlight(String name) {
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
	public void runClientSideEffect(World world, EntityPlayer player) 
	{
		RayTraceResult mop = rayTrace(world, player, true);
		if (mop != null && mop.typeOfHit != RayTraceResult.Type.MISS) {
			BlockPos pos;

			if (mop.typeOfHit == RayTraceResult.Type.ENTITY) {
				pos = mop.entityHit.getPosition();
			}
			else {
				pos = mop.getBlockPos();
				pos = pos.offset(mop.sideHit);
			}

			if (world.isAirBlock(pos)) {
				world.setBlockState(pos, RegisterBlocks.blockLight.getDefaultState());
				world.scheduleUpdate(pos, RegisterBlocks.blockLight, 2);
			}
		}		
	}

	@Override
	public void runClientSideUseEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttachmentType getType() 
	{
		return AttachmentType.FLASH;
	}

}
