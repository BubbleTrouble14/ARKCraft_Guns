package com.bubble.gunmod.client.event;

import static com.bubble.gunmod.a.IConsuming.getAmmunition;
import static com.bubble.gunmod.a.IConsuming.getReloadingProgress;
import static com.bubble.gunmod.a.IConsuming.getTotalAmmunition;
import static com.bubble.gunmod.a.IConsuming.isReloading;

import org.lwjgl.opengl.GL11;

import com.bubble.gunmod.item.ItemRangedWeapon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(Side.CLIENT)
@SideOnly(Side.CLIENT)
public class OverlayHandler {
	@SubscribeEvent
	public static void renderGUIOverlay(RenderGameOverlayEvent.Post e) {
		System.out.println("enter ui");
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer p = mc.player;
		ItemStack stack = p.getHeldItemMainhand();
		if (e.getType().equals(ElementType.HOTBAR)) {
			if (!stack.isEmpty() && stack.getItem() instanceof ItemRangedWeapon) {
				ItemRangedWeapon weapon = (ItemRangedWeapon) stack.getItem();
				boolean loaded = getAmmunition(stack) == weapon.getAmmunitionCapacity();
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glDisable(GL11.GL_LIGHTING);
				int x0 = e.getResolution().getScaledWidth() / 2 - 88 + p.inventory.currentItem * 20;
				int y0 = e.getResolution().getScaledHeight() - 3;
				float f;
				int color;
				if (loaded) {
					f = 1F;
					if (p.getActiveItemStack() == stack) {
						color = 0x60C60000;
					} else {
						color = 0x60348E00;
					}

				} else if (isReloading(stack)) {
					f = Math.min((float) getReloadingProgress(stack) / weapon.getReloadingTime(), 1F);
					color = 0x60EAA800;
				} else {
					f = 0F;
					color = 0;
				}
				Gui.drawRect(x0, y0, x0 + 16, y0 - (int) (f * 16), color);
			}
		} else if (e.getType().equals(ElementType.HELMET)) {
			if (stack != null && stack.getItem() instanceof ItemRangedWeapon) {
				String text = "";
				if (!p.capabilities.isCreativeMode) {
					text = getAmmunition(stack) + "/" + getTotalAmmunition(stack, p);
				} else {
					text = '\u221e' + "";
				}
				int x = e.getResolution().getScaledWidth() - 4 - mc.fontRenderer.getStringWidth(text);
				int y = 20;
				mc.fontRenderer.drawStringWithShadow(text, x, y - 16, 0xFFFFFFFF);
			}
		}
	}
}
