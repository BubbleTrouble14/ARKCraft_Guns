package com.bubble.gunmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterSounds {
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<SoundEvent> event)
	{
		String[] sounds = new String[]{
				"fabricated_pistol_shoot", "fabricated_pistol_shoot_silenced","fabricated_pistol_reload","simple_pistol_shoot",
				"simple_pistol_shoot_silenced","simple_pistol_reload","shotgun_shoot", "shotgun_reload","longneck_rifle_reload",
				"longneck_rifle_shoot","longneck_rifle_shoot_silenced"
		};
		for(int i = 0; i < sounds.length; i++)
		{
			ResourceLocation r = new ResourceLocation(Main.MODID, sounds[i]);
			SoundEvent e = new SoundEvent(r);
			e.setRegistryName(r);
			event.getRegistry().register(e);
		}
	}
}