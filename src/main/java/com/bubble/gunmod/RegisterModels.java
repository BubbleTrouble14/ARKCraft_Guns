package com.bubble.gunmod;

import com.bubble.gunmod.item.WaterGun;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegisterModels 
{
    @GameRegistry.ObjectHolder(Main.MODID + ":test_gun")
    public static WaterGun gun;
	
    @SideOnly(Side.CLIENT)
	public static void initModels()
	{
		ModelLoader.setCustomModelResourceLocation(RegisterItems.item_test_gun, 0,
				new ModelResourceLocation(Main.MODID + ":test_gun", "inventory"));
		
    	gun.initModels();
	}
}