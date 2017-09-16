package com.bubble.gunmod.common.item;

import com.bubble.gunmod.util.Util;

import net.minecraft.item.ItemStack;

public interface IUseInterval { // Change name
	public static final String INTERVAL_TIME_KEY = "interval_time";

	public static void setIntervalTime(ItemStack stack, int intervalTime) {
		Util.getNBT(stack).setInteger(INTERVAL_TIME_KEY, intervalTime);
	}

	public static int getIntervalTime(ItemStack stack) {
		return Util.getNBT(stack).getInteger(INTERVAL_TIME_KEY);
	}

	public static boolean isIntervalPast(ItemStack stack) {
		return getIntervalTime(stack) == 0;
	}

	public int getIntervalDuration();
}
