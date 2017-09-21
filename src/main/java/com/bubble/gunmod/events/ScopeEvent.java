package com.bubble.gunmod.events;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.common.item.ISupporting;
import com.bubble.gunmod.common.item.ItemRangedWeapon;
import com.bubble.gunmod.common.item.attachment.AttachmentType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ScopeEvent {
	
	private static boolean scoping;
	private static final ResourceLocation SCOPE_OVERLAY = new ResourceLocation(Main.MODID, "textures/gui/scope.png");
	private static int swayTicks;
	private static float yawSway;
	private static float pitchSway;
	private static Random random = new Random();

	private static Minecraft mc = Minecraft.getMinecraft();
	private static final int maxTicks = 70;
	
    @SubscribeEvent
    public static void onFOV(FOVUpdateEvent evt)
    {
    	EntityPlayer p = Minecraft.getMinecraft().player;
    	if(p.getHeldItemMainhand() != ItemStack.EMPTY && p.getHeldItemMainhand().getItem() instanceof ISupporting)
    	{
    		if(Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown())
    		{
				AttachmentType at = ISupporting.getAttachmentType(p.getHeldItemMainhand());
    			if(at != null && at.SCOPE != null)
    			{
    				scoping = true;
    				evt.setNewfov(evt.getNewfov() / 6.0F);
    			}
    		}
    		else scoping = false;
    	}
		else scoping = false;
    }

    
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onRender(RenderGameOverlayEvent evt)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if(scoping)
		{
			if (evt.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
				if (mc.gameSettings.thirdPersonView == 0) 
				{
					evt.setCanceled(true);
					showScope();
				}
			}
		}
	}
	
	public static void showScope()
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer thePlayer = mc.player;

		// add sway
		swayTicks++;
		if (swayTicks > maxTicks) {
			// change values here for control of the amount of sway!
			int divider = thePlayer.isSneaking() ? 20 : 7;
			swayTicks = 0;
			yawSway = ((random.nextFloat() * 2 - 1) / divider) / maxTicks;
			pitchSway = ((random.nextFloat() * 2 - 1) / divider) / maxTicks;
		}

		EntityPlayer p = mc.player;
		p.rotationPitch += pitchSway;
		p.rotationYaw += yawSway;
		
		GL11.glPushMatrix();

		RenderScope();
		
		GL11.glPopMatrix();
	}
	
	public static void RenderScope()
	{
		ScaledResolution res = new ScaledResolution(mc);
		double width = res.getScaledWidth_double();
		double height = res.getScaledHeight_double();
		
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        mc.getTextureManager().bindTexture(SCOPE_OVERLAY);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(0.0D, (double)height, -90.0D).tex(0.0D, 1.0D).endVertex();
        vertexbuffer.pos((double)width, (double)height, -90.0D).tex(1.0D, 1.0D).endVertex();
        vertexbuffer.pos((double)width, 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
        vertexbuffer.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
