package com.bubble.gunmod.a;

import java.util.stream.Collectors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public interface IConsuming {
	public boolean isValidAmmunition(ItemAmmunition item);

	public static void consumeAmmunition(EntityPlayer player, ItemStack stack, ItemAmmunition item) {
		IConsuming stackItem = (IConsuming) stack.getItem();
		int cap = stackItem.getAmmunitionCapacity();
		NonNullList<ItemStack> inv = player.inventory.mainInventory;

		for (ItemStack i : inv) {
			if (i.getItem() == item) {
				setAmmunitionItem(stack, item);
				int amo = i.getCount();
				int con = amo > cap ? cap : amo;
				setAmmunition(stack, con + getAmmunition(stack));
				i.shrink(con);
				cap -= con;
				if (cap == 0)
					return;
			}
		}
	}

	public static final String AMMUNITION_AMOUNT_KEY = "ammunition";
	public static final String AMMUNITION_TYPE_KEY = "ammunitionitem";
	public static final String RELOADING_PROGRESS = "reloadingtime";

	public static int getAmmunition(ItemStack stack) {
		NBTTagCompound nbt = Util.getNBT(stack);
		return nbt.getInteger(AMMUNITION_AMOUNT_KEY);
	}

	public static int getTotalAmmunition(ItemStack stack, EntityPlayer p) {
		ItemAmmunition item = getAmmunitionItem(stack);
		int am = getAmmunition(stack);
		for (ItemStack is : p.inventory.mainInventory.stream().filter(i -> !i.isEmpty() && i.getItem() == item)
				.collect(Collectors.toList()))
			am += is.getCount();
		return am;
	}

	public static void setAmmunition(ItemStack stack, int amount) {
		NBTTagCompound nbt = Util.getNBT(stack);
		nbt.setInteger(AMMUNITION_AMOUNT_KEY, amount);
	}

	public static ItemAmmunition getAmmunitionItem(ItemStack stack) {
		NBTTagCompound nbt = Util.getNBT(stack);
		String item = nbt.getString(AMMUNITION_TYPE_KEY);
		return !item.isEmpty() ? (ItemAmmunition) Item.getByNameOrId(item) : null;
	}

	public static void setAmmunitionItem(ItemStack stack, ItemAmmunition ammunition) {
		NBTTagCompound nbt = Util.getNBT(stack);
		ResourceLocation resourcelocation = Item.REGISTRY.getNameForObject(ammunition);
		nbt.setString(AMMUNITION_TYPE_KEY, resourcelocation.toString());
	}

	public static int getReloadingProgress(ItemStack stack) {
		NBTTagCompound nbt = Util.getNBT(stack);
		return nbt.getInteger(RELOADING_PROGRESS);
	}

	public static void setReloadingProgress(ItemStack stack, int value) {
		NBTTagCompound nbt = Util.getNBT(stack);
		nbt.setInteger(RELOADING_PROGRESS, value);
	}

	public static boolean isReloading(ItemStack stack) {
		return getReloadingProgress(stack) > 0;
	}

	public int getReloadingTime();

	public int getAmmunitionCapacity();
}
