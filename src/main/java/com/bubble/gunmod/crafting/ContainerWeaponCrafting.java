package com.bubble.gunmod.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ContainerWeaponCrafting extends Container {
    private InventoryPlayer playerInventory;
    private int slotCount = 0;
    private WeaponCraftingSlot craftingSlot;

    public ContainerWeaponCrafting(InventoryPlayer playerInventory) {
	this.playerInventory = playerInventory;

	int slot_start_x = 100;
	int slot_start_y = 100;

	playerInventory.mainInventory.forEach(i -> {
	    addSlotToContainer(new Slot(playerInventory, slotCount, slot_start_x + 16 * slotCount / 9,
		    slot_start_y + 16 * slotCount / 4));
	    slotCount++;
	});
	this.craftingSlot = new WeaponCraftingSlot(50, 50);
	addSlotToContainer(craftingSlot);
    }

    @Override
    public boolean enchantItem(EntityPlayer playerIn, int id) {
	if (id == 0) {
	    this.moveLeft();
	    return true;
	} else if (id == 1) {
	    this.moveRight();
	    return true;
	} else
	    return super.enchantItem(playerIn, id);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
	return true;
    }

    private static final List<WeaponRecipe> recipes = new ArrayList<WeaponRecipe>();

    public static void registerRecipe(WeaponRecipe recipe) {
	recipes.add(recipe);
    }

    public static void registerRecipe(ItemStack output, ItemStack... inputs) {
	recipes.add(new WeaponRecipe(output, inputs));
    }

    public static class WeaponRecipe {
	private ItemStack output;
	private NonNullList<ItemStack> inputs;

	public WeaponRecipe() {
	    this.inputs = NonNullList.create();
	}

	public WeaponRecipe(ItemStack output, ItemStack... inputs) {
	    this();
	    this.output = output;
	    for (ItemStack in : inputs)
		addInput(in);
	}

	public void addInput(ItemStack itemStack) {
	    inputs.add(itemStack);
	}

	public void setOutput(ItemStack output) {
	    this.output = output;
	}

	public boolean canCraft(EntityPlayer player) {
	    Map<Item, Integer> countMap = new HashMap<>();
	    inputs.forEach(i -> {
		int count = countMap.getOrDefault(i.getItem(), 0);
		countMap.put(i.getItem(), count + i.getCount());
	    });
	    for (Entry<Item, Integer> e : countMap.entrySet()) {
		int count = e.getValue();
		for (ItemStack i : player.inventory.mainInventory) {
		    if (i.getItem() == e.getKey()) {
			count -= i.getCount();
		    }
		}
		if (count > 0)
		    return false;
	    }
	    return true;
	}

	public void consume(EntityPlayer player) {
	    Map<Item, Integer> countMap = new HashMap<>();
	    inputs.forEach(i -> {
		int count = countMap.getOrDefault(i.getItem(), 0);
		countMap.put(i.getItem(), count + i.getCount());
	    });
	    for (Entry<Item, Integer> e : countMap.entrySet()) {
		int count = e.getValue();
		for (ItemStack i : player.inventory.mainInventory) {
		    if (i.getItem() == e.getKey()) {
			int min = count < i.getCount() ? count : i.getCount();
			i.shrink(min);
			count -= min;
			if (count == 0)
			    break;
		    }
		}
	    }
	}
    }

    private static class WeaponCraftingSlot extends Slot {
	private int index = 0;
	private final int maxIndex;

	public WeaponCraftingSlot(int xPosition, int yPosition) {
	    super(null, 0, xPosition, yPosition);
	    this.maxIndex = recipes.size() - 1;
	}

	private void moveLeft() {
	    if (index == 0)
		index = maxIndex;
	    else
		index--;
	}

	private void moveRight() {
	    if (index == maxIndex)
		index = 0;
	    else
		index++;
	}

	@Override
	public boolean canTakeStack(EntityPlayer player) {
	    return recipes.get(index).canCraft(player);
	}

	@Override
	public ItemStack getStack() {
	    return recipes.get(index).output;
	}

	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
	    recipes.get(index).consume(thePlayer);
	    return super.onTake(thePlayer, stack);
	}

	@Override
	public void onSlotChanged() {
	}

	@Override
	public ItemStack decrStackSize(int amount) {
	    return recipes.get(index).output.copy();
	}
    }

    public void moveLeft() {
	this.craftingSlot.moveLeft();
    }

    public void moveRight() {
	this.craftingSlot.moveRight();
    }
}
