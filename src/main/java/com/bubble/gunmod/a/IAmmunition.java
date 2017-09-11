package com.bubble.gunmod.a;

import net.minecraft.entity.player.EntityPlayer;

public interface IAmmunition {
	public EntityProjectile createProjectile(EntityPlayer player);
}
