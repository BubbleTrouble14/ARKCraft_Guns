package com.bubble.gunmod.common.item;

import static com.bubble.gunmod.common.item.IConsuming.cancelReloading;
import static com.bubble.gunmod.common.item.IConsuming.consumeAmmunition;
import static com.bubble.gunmod.common.item.IConsuming.getAmmunition;
import static com.bubble.gunmod.common.item.IConsuming.getAmmunitionItem;
import static com.bubble.gunmod.common.item.IConsuming.getReloadingProgress;
import static com.bubble.gunmod.common.item.IConsuming.isReloading;
import static com.bubble.gunmod.common.item.IConsuming.setAmmunition;
import static com.bubble.gunmod.common.item.IConsuming.setReloadingProgress;
import static com.bubble.gunmod.common.item.ISoundEffects.soundFire;
import static com.bubble.gunmod.common.item.IUseInterval.getIntervalTime;
import static com.bubble.gunmod.common.item.IUseInterval.isIntervalPast;
import static com.bubble.gunmod.common.item.IUseInterval.setIntervalTime;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class ItemRangedWeaponBase extends ItemBase implements IUseInterval, IConsuming, ISoundEffects {

	public ItemRangedWeaponBase(String name) 
	{
		super(name);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("reloading"), (stack, world, entity) -> {
			if (isReloading(stack))
				return 1;
			return 0;
		});
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItemMainhand();
		if (!isReloading(stack) && isIntervalPast(stack)) {
			if (getAmmunition(stack) == 0 && !player.capabilities.isCreativeMode) {
				// Reload
				setReloadingProgress(stack, getReloadingTime());
			} else {
				// Shoot
				if (!player.capabilities.isCreativeMode)
					setAmmunition(stack, getAmmunition(stack) - 1);
					soundFire(stack, player, world);
				world.spawnEntity(getAmmunitionItem(stack).createProjectile(player));
				setIntervalTime(stack, getIntervalDuration());
				if (getAmmunition(stack) == 0 && !player.capabilities.isCreativeMode)
					setReloadingProgress(stack, getReloadingTime());
			}
		}
		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		EntityPlayer player = (EntityPlayer) entityIn;
		if (isReloading(stack)) {
			if (isSelected) {
				int time = getReloadingProgress(stack) - 1;
				setReloadingProgress(stack, time);
				if (time == 0) 
				{
					for (ItemStack s : player.inventory.mainInventory) {
						if (!s.isEmpty() && s.getItem() instanceof ItemAmmunition) {
							if (isValidAmmunition((ItemAmmunition) s.getItem())) {
								consumeAmmunition(player, stack, (ItemAmmunition) s.getItem());
								break;
							}
						}
					}
				}
			} else
				cancelReloading(stack);
		}
		if (!isIntervalPast(stack)) {
			setIntervalTime(stack, getIntervalTime(stack) - 1);
		}
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		if (!slotChanged && oldStack.getItem() == newStack.getItem())
			return false;
		return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
	}
}
