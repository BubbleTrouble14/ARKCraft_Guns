package com.bubble.gunmod.common.handlers;

import com.bubble.gunmod.client.gui.GuiAttachment;
import com.bubble.gunmod.common.container.ContainerAttachment;
import com.bubble.gunmod.common.item.ISupporting;
import com.bubble.gunmod.crafting.ContainerWeaponCrafting;
import com.bubble.gunmod.crafting.GuiWeaponCrafting;
import com.bubble.gunmod.proxy.CommonProxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	if (id == CommonProxy.GUI.ATTACHMENTS.id) {
	    if (player.getHeldItemMainhand().getItem() instanceof ISupporting)
		return new ContainerAttachment(player, player.inventory, player.getHeldItemMainhand());
	} else if (id == CommonProxy.GUI.CRAFTING.id) {
	    return new ContainerWeaponCrafting(player.inventory);
	}
	return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

	if (id == CommonProxy.GUI.ATTACHMENTS.id) {
	    if (player.getHeldItemMainhand().getItem() instanceof ISupporting)
		return new GuiAttachment(player, player.inventory, player.getHeldItemMainhand());
	} else if (id == CommonProxy.GUI.CRAFTING.id) {
	    return new GuiWeaponCrafting(new ContainerWeaponCrafting(player.inventory));
	}
	return null;
    }
}
