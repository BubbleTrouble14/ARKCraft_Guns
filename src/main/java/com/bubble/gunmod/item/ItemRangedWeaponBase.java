package com.bubble.gunmod.item;

import static com.bubble.gunmod.a.IConsuming.cancelReloading;
import static com.bubble.gunmod.a.IConsuming.consumeAmmunition;
import static com.bubble.gunmod.a.IConsuming.getAmmunition;
import static com.bubble.gunmod.a.IConsuming.getAmmunitionItem;
import static com.bubble.gunmod.a.IConsuming.getReloadingProgress;
import static com.bubble.gunmod.a.IConsuming.isReloading;
import static com.bubble.gunmod.a.IConsuming.setAmmunition;
import static com.bubble.gunmod.a.IConsuming.setReloadingProgress;
import static com.bubble.gunmod.a.IUseInterval.getIntervalTime;
import static com.bubble.gunmod.a.IUseInterval.isIntervalPast;
import static com.bubble.gunmod.a.IUseInterval.setIntervalTime;
import static com.bubble.gunmod.item.ISoundEffects.soundEmpty;
import static com.bubble.gunmod.item.ISoundEffects.soundFire;
import static com.bubble.gunmod.item.ISoundEffects.soundReload;



import com.bubble.gunmod.Main;
import com.bubble.gunmod.a.IConsuming;
import com.bubble.gunmod.a.IUseInterval;
import com.bubble.gunmod.a.ItemAmmunition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class ItemRangedWeaponBase extends Item implements IUseInterval, IConsuming, ISoundEffects {

	public ItemRangedWeaponBase(String name) {
		setMaxStackSize(1);
		setRegistryName(name);
		setUnlocalizedName(Main.MODID + "." + name);
		addPropertyOverride(new ResourceLocation("reloading"), (stack, world, entity) -> {
			if (isReloading(stack))
				return 1;
			return 0;
		});
		setCreativeTab(Main.tabGuns);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItemMainhand();
		if (!isReloading(stack) && isIntervalPast(stack)) {
			if (getAmmunition(stack) == 0) {
				// Reload
				setReloadingProgress(stack, getReloadingTime());
			} else {
				// Shoot
				if (!player.capabilities.isCreativeMode)
					setAmmunition(stack, getAmmunition(stack) - 1);
				soundFire(stack, player);
				world.spawnEntity(getAmmunitionItem(stack).createProjectile(player));
				setIntervalTime(stack, getIntervalDuration());
				if (getAmmunition(stack) == 0)
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
				if (time == 0) {
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
