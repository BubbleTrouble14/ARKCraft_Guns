package com.bubble.gunmod.proxy;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.common.handlers.GuiHandler;
import com.bubble.gunmod.common.network.OpenAttachmentInventory;
import com.bubble.gunmod.common.network.ReloadMessage;
import com.bubble.gunmod.crafting.ContainerWeaponCrafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public abstract class CommonProxy {
    public CommonProxy() {
    }

    public enum GUI {
	ATTACHMENTS, CRAFTING;

	public final int id;

	GUI() {
	    this.id = getNextId();
	}

	static int idCounter = 0;

	private static int getNextId() {
	    return idCounter++;
	}

	public final int getID() {
	    return id;
	}
    }

    public void preInit(FMLPreInitializationEvent event) {
	setupNetwork(event);
	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance(), new GuiHandler());
    }

    public void init(FMLInitializationEvent event) {
	registerEventHandlers();
	// Recipes.init();
	if (Loader.isModLoaded("arkcraft")) {

	} else {
	    ContainerWeaponCrafting.registerRecipe(new ItemStack(RegisterItems.fabricated_pistol),
		    new ItemStack(Items.GUNPOWDER, 10));
	    ContainerWeaponCrafting.registerRecipe(new ItemStack(RegisterItems.laser),
		    new ItemStack(Items.GUNPOWDER, 10));
	}
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    protected void registerEventHandlers() {

    }

    private final void setupNetwork(FMLPreInitializationEvent event) {
	SimpleNetworkWrapper modChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID);
	Main.modChannel = modChannel;

	int id = 0;
	modChannel.registerMessage(OpenAttachmentInventory.Handler.class, OpenAttachmentInventory.class, id++,
		Side.SERVER);
	modChannel.registerMessage(ReloadMessage.ReloadMessageHandler.class, ReloadMessage.class, id++, Side.SERVER);
	// modChannel.registerMessage(ReloadFinished.Handler.class,
	// ReloadFinished.class, id++, Side.CLIENT);
    }
}