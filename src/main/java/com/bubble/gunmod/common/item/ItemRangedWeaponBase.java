package com.bubble.gunmod.common.item;

import static com.bubble.gunmod.common.item.IConsuming.cancelReloading;
import static com.bubble.gunmod.common.item.IConsuming.consumeAmmunition;
import static com.bubble.gunmod.common.item.IConsuming.getAmmunition;
import static com.bubble.gunmod.common.item.IConsuming.getAmmunitionItem;
import static com.bubble.gunmod.common.item.IConsuming.getReloadingProgress;
import static com.bubble.gunmod.common.item.IConsuming.isReloading;
import static com.bubble.gunmod.common.item.IConsuming.setAmmunition;
import static com.bubble.gunmod.common.item.IConsuming.setReloadingProgress;
import static com.bubble.gunmod.common.item.IUseInterval.getIntervalTime;
import static com.bubble.gunmod.common.item.IUseInterval.isIntervalPast;
import static com.bubble.gunmod.common.item.IUseInterval.setIntervalTime;

import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class ItemRangedWeaponBase extends ItemBase implements IUseInterval, IConsuming {

	private int durability;
	
	public ItemRangedWeaponBase(String name, int durability) 
	{
		super(name);
		this.durability = durability;
		setMaxStackSize(1);
		setMaxDamage(durability);
	}

	@Override
	protected void registerPropertyOverrides() {
		addPropertyOverride(new ResourceLocation("reloading"), (stack, player, world) -> {
			if (isReloading(stack)) {
				//TODOD return 1
				return 0;
			}
			return 0;
		});
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) 
	{
		//Scope
		return super.onItemRightClick(world, player, hand);
	}
	
	public void onLeftClick(World world, EntityPlayer player)
	{
		ItemStack stack = player.getHeldItemMainhand();
	//	System.out.println("reached");
		if (!isReloading(stack) && isIntervalPast(stack)) {
			if (getAmmunition(stack) == 0 && !player.isCreative()) {
				// Reload
				if (!startReload(stack, player, world)) {
					soundEmpty(stack, player, world);
				}
			} else {
				// Shoot
				if (!player.isCreative()) {
					setAmmunition(stack, getAmmunition(stack) - 1);
				}
				soundFire(stack, player, world);
				if (!player.isCreative()) {
					world.spawnEntity(getAmmunitionItem(stack).createProjectile(player));
					stack.damageItem(1, player);
				} else
					world.spawnEntity(((IConsuming) stack.getItem()).getDefaultAmmunition().createProjectile(player));
				setIntervalTime(stack, getIntervalDuration());
				if (!player.isCreative() && getAmmunition(stack) == 0)
					startReload(stack, player, world);
			}
		}
	}

	static boolean notClickedYet = true;

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

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		cancelReloading(entityItem.getItem());
		return super.onEntityItemUpdate(entityItem);
	}

	static void soundFire(ItemStack stack, EntityPlayer p, World w) {
		String soundPath = stack.getItem().getRegistryName() + "_shoot";
		w.playSound(p, p.getPosition(), SoundEvent.REGISTRY.getObject(new ResourceLocation(soundPath)),
				SoundCategory.NEUTRAL, 0.7F, 0.9F / (0.2F + 0.0F));
	}

	static void soundEmpty(ItemStack stack, EntityPlayer p, World w) {
		w.playSound(p, p.getPosition(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.NEUTRAL, 1.0F, 1.0F / 0.8F);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("Durability " + this.getDamage(stack) + "/" + this.durability);
	}
	
	@Override
	public int getItemEnchantability()
	{
		return 0;
	}
	
}
