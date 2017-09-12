package com.bubble.gunmod;

import com.bubble.gunmod.item.ItemFabricatedPistol;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
@SideOnly(Side.CLIENT)
public class RegisterModels {
	@GameRegistry.ObjectHolder(Main.MODID + ":test_gun") //this looks cool -- look into it
	public static ItemFabricatedPistol fabricated_pistol;

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(RegisterItems.fabricated_pistol, 0,
				new ModelResourceLocation(Main.MODID + ":test_gun", "inventory"));
	}
}
