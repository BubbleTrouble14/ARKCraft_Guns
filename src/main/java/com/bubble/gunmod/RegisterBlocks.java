package com.bubble.gunmod;

import com.bubble.gunmod.common.block.BlockBase;
import com.bubble.gunmod.common.block.BlockLight;
import com.bubble.gunmod.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterBlocks {
    public static BlockLight blockLight;
    public static BlockBase crafting;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
	blockLight = new BlockLight("block_light");
	event.getRegistry().register(blockLight);
	crafting = new BlockBase(Material.WOOD, "block_crafting") {
	    @Override
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
		    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.openGui(Main.instance(), CommonProxy.GUI.CRAFTING.id, playerIn.world, pos.getX(), pos.getY(),
			pos.getZ());
		return false;
	    }
	};
	event.getRegistry().register(crafting);
    }
}
