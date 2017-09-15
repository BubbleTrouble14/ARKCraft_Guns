package com.bubble.gunmod.item;

import com.bubble.gunmod.Main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public interface ISoundEffects 
{
	static void soundFire(ItemStack stack, EntityPlayer p)
	{
		String soundPath = Main.MODID + ":" + stack.getUnlocalizedName() + "_shoot";
		p.world.playSound(p, p.getPosition(), SoundEvent.REGISTRY.getObject(new ResourceLocation(soundPath)),
				SoundCategory.PLAYERS, 0.7F, 0.9F / (0.2F + 0.0F));
	}
	
	static void soundReload(ItemStack stack, EntityPlayer p)
	{
		String soundPath = Main.MODID + ":" + stack.getUnlocalizedName() + "_reload";
		p.world.playSound(p, p.getPosition(), SoundEvent.REGISTRY.getObject(new ResourceLocation(soundPath)),
				SoundCategory.PLAYERS, 0.7F, 0.9F / (0.2F + 0.0F));
	}
	
	static void soundEmpty(ItemStack stack, EntityPlayer p)
	{
		p.world.playSound(p, p.getPosition(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS,
				1.0F, 1.0F / 0.8F);
	}
}
