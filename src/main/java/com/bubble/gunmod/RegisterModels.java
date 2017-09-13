package com.bubble.gunmod;

import java.util.ArrayList;
import java.util.List;

import com.bubble.gunmod.a.ISupporting;
import com.bubble.gunmod.item.ItemFabricatedPistol;
import com.bubble.gunmod.item.ItemLongneckRifle;
import com.bubble.gunmod.item.ItemShotgun;
import com.bubble.gunmod.item.ItemSimplePistol;
import com.bubble.gunmod.item.attachment.ItemFlashlight;
import com.bubble.gunmod.item.attachment.ItemLaser;
import com.bubble.gunmod.item.attachment.ItemScope;
import com.bubble.gunmod.item.attachment.ItemSilencer;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
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
	@GameRegistry.ObjectHolder(Main.MODID + ":fabricated_pistol") //this looks cool -- look into it
	public static ItemFabricatedPistol fabricated_pistol;
	@GameRegistry.ObjectHolder(Main.MODID + ":simple_pistol")
	public static ItemSimplePistol simple_pistol;
	@GameRegistry.ObjectHolder(Main.MODID + ":shotgun")
	public static ItemShotgun shotgun;
	@GameRegistry.ObjectHolder(Main.MODID + ":longneck_rifle")
	public static ItemLongneckRifle longneck_rifle;
	
	@GameRegistry.ObjectHolder(Main.MODID + ":scope")
	public static ItemScope scope;
	@GameRegistry.ObjectHolder(Main.MODID + ":flashlight")
	public static ItemFlashlight flashlight;
	@GameRegistry.ObjectHolder(Main.MODID + ":silencer")
	public static ItemSilencer silencer;
	@GameRegistry.ObjectHolder(Main.MODID + ":laser")
	public static ItemLaser laser;

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) 
	{
		// Attachments
		ModelLoader.setCustomModelResourceLocation(scope, 0,	new ModelResourceLocation(scope.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(flashlight, 0,new ModelResourceLocation(flashlight.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(silencer, 0, new ModelResourceLocation(silencer.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(laser, 0, new ModelResourceLocation(laser.getRegistryName(), "inventory"));

		// Ammo
		ModelLoader.setCustomModelResourceLocation(RegisterItems.simple_rifle_ammo, 0, new ModelResourceLocation(Main.MODID + ":" + "simple_rifle_ammo", "inventory"));
		ModelLoader.setCustomModelResourceLocation(RegisterItems.simple_shotgun_ammo, 0, new ModelResourceLocation(Main.MODID + ":" + "simple_shotgun_ammo", "inventory"));
		ModelLoader.setCustomModelResourceLocation(RegisterItems.simple_bullet, 0, new ModelResourceLocation(Main.MODID + ":" + "simple_bullet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(RegisterItems.advanced_bullet, 0, new ModelResourceLocation(Main.MODID + ":" + "advanced_bullet", "inventory"));
		
		List<Item> weapons = new ArrayList<>();
		weapons.add(fabricated_pistol);
		weapons.add(simple_pistol);
		weapons.add(shotgun);
		weapons.add(longneck_rifle);
		for(Item i : weapons)
		{
			initModels(i);
		}
	}
	
	public static void initModels(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		
		if(!(item instanceof ISupporting))
		{
			ModelResourceLocation scope = new ModelResourceLocation(item.getRegistryName() + "_scope", "inventory");
			ModelResourceLocation scopereload = new ModelResourceLocation(item.getRegistryName() + "_scope_reload", "inventory");
			ModelResourceLocation laser = new ModelResourceLocation(item.getRegistryName() + "_laser", "inventory");
			ModelResourceLocation laserreload = new ModelResourceLocation(item.getRegistryName() + "_laser_reload", "inventory");
			ModelResourceLocation silencer = new ModelResourceLocation(item.getRegistryName()+ "_silencer", "inventory");
			ModelResourceLocation silencerreload = new ModelResourceLocation(item.getRegistryName() + "_silencer_reload", "inventory");
			ModelResourceLocation flashlight = new ModelResourceLocation(item.getRegistryName() + "_flashlight", "inventory");
			ModelResourceLocation flashlightreload = new ModelResourceLocation(item.getRegistryName() + "_flashlight_reload", "inventory");
			ModelResourceLocation reload = new ModelResourceLocation(item.getRegistryName() + "_reload", "inventory");
			
			ModelBakery.registerItemVariants(item, scope, laser, silencer, flashlight, reload, scopereload,
					laserreload, silencerreload, flashlightreload);
		}
	}
}
