package com.bubble.gunmod.common.block;

import com.bubble.gunmod.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    public BlockBase(Material materialIn, String name) {
	super(materialIn);
	setRegistryName(name);
	setUnlocalizedName(Main.MODID + "." + name);
	setCreativeTab(Main.tabGuns);
    }
}
