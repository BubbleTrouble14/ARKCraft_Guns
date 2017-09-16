package com.bubble.gunmod;

import com.bubble.gunmod.common.block.BlockLight;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterBlocks 
{
	public static BlockLight blockLight;
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		blockLight = new BlockLight("block_light");
		event.getRegistry().register(blockLight);
	}
}
