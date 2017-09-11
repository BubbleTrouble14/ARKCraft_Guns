package com.bubble.gunmod.proxy;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.RegisterModels;
import com.bubble.gunmod.a.ISupporting;
import com.bubble.gunmod.a.ItemRangedWeapon;
import com.bubble.gunmod.client.KeyBindings;
import com.bubble.gunmod.common.network.OpenAttachmentInventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{	
	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);	
		
        registerEntityModels();
	}
	
	@Override
	protected void registerEventHandlers() 
	{
		super.registerEventHandlers();
//		MinecraftForge.EVENT_BUS.register(new GUIOverlayReloading());
//		MinecraftForge.EVENT_BUS.register(KeyBindingEvent.class);
		KeyBindings.init();
//		MinecraftForge.EVENT_BUS.register(ClickEvent.class);
//		MinecraftForge.EVENT_BUS.register(ScopeEvent.class);
	}
	
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
	
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) 
    {
        RegisterModels.initModels();
    }
	
	public void registerEntityModels()
	{
//		RenderingRegistry.registerEntityRenderingHandler(EntityAdvancedBullet.class, new IRenderFactory<EntityAdvancedBullet>() {
//			@Override
//			public Render<EntityAdvancedBullet> createRenderFor(RenderManager manager) {
//				return new RenderAdvancedBullet(manager);
//			}
//		});
//		RenderingRegistry.registerEntityRenderingHandler(EntitySimpleBullet.class, new IRenderFactory<EntitySimpleBullet>(){
//			@Override
//			public Render<EntitySimpleBullet> createRenderFor(RenderManager manager) {
//				return new RenderSimpleBullet(manager);
//			}
//		});
//		RenderingRegistry.registerEntityRenderingHandler(EntitySimpleRifleAmmo.class, new IRenderFactory<EntitySimpleRifleAmmo>() {
//			@Override
//			public Render<EntitySimpleRifleAmmo> createRenderFor(RenderManager manager) {
//				return new RenderSimpleRifleAmmo(manager);
//			}
//		});
//		RenderingRegistry.registerEntityRenderingHandler(EntitySimpleShotgunAmmo.class, new IRenderFactory<EntitySimpleShotgunAmmo>() {
//			@Override
//			public Render<EntitySimpleShotgunAmmo> createRenderFor(RenderManager manager) {
//				return new RenderSimpleShotgunAmmo(manager);
//			}
//		});
	}
}