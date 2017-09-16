package com.bubble.gunmod.common.item;

import com.bubble.gunmod.common.entity.EntityProjectile;

import net.minecraft.entity.player.EntityPlayer;

public interface IAmmunition {
	public EntityProjectile createProjectile(EntityPlayer player);
}
