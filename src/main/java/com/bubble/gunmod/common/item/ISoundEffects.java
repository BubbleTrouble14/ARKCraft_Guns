package com.bubble.gunmod.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public interface ISoundEffects 
{
	static void soundFire(ItemStack stack, EntityPlayer p, World w)
	{
		String soundPath = stack.getItem().getRegistryName() + "_shoot";
		w.playSound(p, p.getPosition(), SoundEvent.REGISTRY.getObject(new ResourceLocation(soundPath)), SoundCategory.NEUTRAL, 0.7F, 0.9F / (0.2F + 0.0F));
	}
	
	static void soundReload(ItemStack stack, EntityPlayer p, World w)
	{
		String soundPath = stack.getItem().getRegistryName() + "_reload";
		w.playSound(p, p.getPosition(), SoundEvent.REGISTRY.getObject(new ResourceLocation(soundPath)),	SoundCategory.NEUTRAL, 0.7F, 0.9F / (0.2F + 0.0F));
	}
	
	static void soundEmpty(ItemStack stack, EntityPlayer p, World w)
	{
		w.playSound(p, p.getPosition(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.NEUTRAL, 1.0F, 1.0F / 0.8F);
	}
}
