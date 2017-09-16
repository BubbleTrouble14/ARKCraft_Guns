package com.bubble.gunmod;

import com.bubble.gunmod.common.item.ItemRangedWeapon;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
@SideOnly(Side.CLIENT)
public class RegisterModels {
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (Item item : RegisterItems.getItemlist()) {
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
			if (item instanceof ItemRangedWeapon) {
				initModels(item);
			}
		}
	}

	public static void initModels(Item item) {
		ModelResourceLocation scope = new ModelResourceLocation(item.getRegistryName() + "_scope", "inventory");
		ModelResourceLocation scopereload = new ModelResourceLocation(item.getRegistryName() + "_scope_reload",
				"inventory");
		ModelResourceLocation laser = new ModelResourceLocation(item.getRegistryName() + "_laser", "inventory");
		ModelResourceLocation laserreload = new ModelResourceLocation(item.getRegistryName() + "_laser_reload",
				"inventory");
		ModelResourceLocation silencer = new ModelResourceLocation(item.getRegistryName() + "_silencer", "inventory");
		ModelResourceLocation silencerreload = new ModelResourceLocation(item.getRegistryName() + "_silencer_reload",
				"inventory");
		ModelResourceLocation flashlight = new ModelResourceLocation(item.getRegistryName() + "_flashlight",
				"inventory");
		ModelResourceLocation flashlightreload = new ModelResourceLocation(
				item.getRegistryName() + "_flashlight_reload", "inventory");
		ModelResourceLocation reload = new ModelResourceLocation(item.getRegistryName() + "_reload", "inventory");

		ModelBakery.registerItemVariants(item, scope, laser, silencer, flashlight, reload, scopereload, laserreload,
				silencerreload, flashlightreload);
	}
}
