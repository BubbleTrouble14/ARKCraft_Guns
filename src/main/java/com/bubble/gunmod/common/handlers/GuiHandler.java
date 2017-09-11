package com.bubble.gunmod.common.handlers;

import com.bubble.gunmod.a.ContainerAttachment;
import com.bubble.gunmod.a.GuiAttachment;
import com.bubble.gunmod.a.ISupporting;
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
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		 if (id == CommonProxy.GUI.ATTACHMENTS.id) {
			if (player.getHeldItemMainhand().getItem() instanceof ISupporting)
				return new GuiAttachment(player, player.inventory, player.getHeldItemMainhand());
		}
		return null;
	}
}
