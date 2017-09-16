package com.bubble.gunmod.events;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.client.KeyBindings;
import com.bubble.gunmod.common.item.IConsuming;
import com.bubble.gunmod.common.item.ISupporting;
import com.bubble.gunmod.common.network.OpenAttachmentInventory;
import com.bubble.gunmod.common.network.ReloadMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(Side.CLIENT)
@SideOnly(Side.CLIENT)
public class KeyBindingEventHandler {
	@SubscribeEvent
	public static void onPlayerKeypressed(InputEvent.KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (KeyBindings.attachment.isPressed()) {
			if (player.getHeldItemMainhand().getItem() instanceof ISupporting) {
				Main.modChannel.sendToServer(new OpenAttachmentInventory());
				// System.out.println("pressed M");
			}
		} else if (KeyBindings.reload.isPressed()) {
			if (player.getHeldItemMainhand().getItem() instanceof IConsuming && IConsuming
					.getAmmunition(player.getHeldItemMainhand()) < ((IConsuming) player.getHeldItemMainhand().getItem())
							.getAmmunitionCapacity()) {
				Main.modChannel.sendToServer(new ReloadMessage());
			}
		}
	}
}
