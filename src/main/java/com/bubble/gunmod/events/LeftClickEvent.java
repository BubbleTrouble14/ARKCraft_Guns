package com.bubble.gunmod.events;

import org.lwjgl.input.Mouse;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.common.item.ItemRangedWeapon;
import com.bubble.gunmod.common.item.ItemRangedWeaponBase;
import com.bubble.gunmod.common.network.LeftClickedMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(Side.CLIENT)
@SideOnly(Side.CLIENT)
public class LeftClickEvent {

	private static boolean notclicked = true;

	@SubscribeEvent
	public static void onMouseEvent(MouseEvent evt) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer thePlayer = mc.player;
		ItemStack rightHandStack = thePlayer.getHeldItemMainhand();
		if (rightHandStack.getItem() instanceof ItemRangedWeaponBase) {
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == 0) {
					evt.setCanceled(true);
					notclicked = true;
				}
			} else {
				if (notclicked) {
					if (Mouse.getEventButton() == 0) {
						notclicked = false;
						Main.modChannel.sendToServer(new LeftClickedMessage());
						ItemRangedWeapon i = (ItemRangedWeapon)rightHandStack.getItem();
						i.onLeftClick(thePlayer.world, thePlayer);
					}
				}
			}
		}
	}
}
