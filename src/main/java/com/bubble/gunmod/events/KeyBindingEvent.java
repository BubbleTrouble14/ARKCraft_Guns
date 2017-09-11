package com.bubble.gunmod.events;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.a.ISupporting;
import com.bubble.gunmod.a.ItemRangedWeapon;
import com.bubble.gunmod.client.KeyBindings;
import com.bubble.gunmod.common.network.OpenAttachmentInventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;


public class KeyBindingEvent 
{
	@SubscribeEvent
	public static void onPlayerKeypressed(InputEvent.KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (KeyBindings.attachment.isPressed()) {
			if (player.getHeldItemMainhand() != null
					&& (player.getHeldItemMainhand().getItem() instanceof ItemRangedWeapon
							|| player.getHeldItemMainhand().getItem() instanceof ISupporting)) {
				Main.modChannel.sendToServer(new OpenAttachmentInventory());
				System.out.println("pressed M");
			}
		}
	}
}
