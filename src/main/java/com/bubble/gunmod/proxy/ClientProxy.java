package com.bubble.gunmod.proxy;

import com.bubble.gunmod.client.KeyBindings;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		registerEntityModels();
	}

	@Override
	protected void registerEventHandlers() {
		super.registerEventHandlers();
		// MinecraftForge.EVENT_BUS.register(new GUIOverlayReloading());
		// MinecraftForge.EVENT_BUS.register(KeyBindingEvent.class);
		KeyBindings.init();
		// MinecraftForge.EVENT_BUS.register(ClickEvent.class);
		// MinecraftForge.EVENT_BUS.register(ScopeEvent.class);
	}

	public void registerEntityModels() {
		// TODO @BubbleTrouble add the Render classes!
		// RenderingRegistry.registerEntityRenderingHandler(EntityAdvancedBullet.class,
		// new IRenderFactory<EntityAdvancedBullet>() {
		// @Override
		// public Render<EntityAdvancedBullet> createRenderFor(RenderManager manager) {
		// return new RenderAdvancedBullet(manager);
		// }
		// });
		// RenderingRegistry.registerEntityRenderingHandler(EntitySimpleBullet.class,
		// new IRenderFactory<EntitySimpleBullet>() {
		// @Override
		// public Render<EntitySimpleBullet> createRenderFor(RenderManager manager) {
		// return new RenderSimpleBullet(manager);
		// }
		// });
		// RenderingRegistry.registerEntityRenderingHandler(EntitySimpleRifleAmmo.class,
		// new IRenderFactory<EntitySimpleRifleAmmo>() {
		// @Override
		// public Render<EntitySimpleRifleAmmo> createRenderFor(RenderManager manager) {
		// return new RenderSimpleRifleAmmo(manager);
		// }
		// });
		// RenderingRegistry.registerEntityRenderingHandler(EntitySimpleShotgunAmmo.class,
		// new IRenderFactory<EntitySimpleShotgunAmmo>() {
		// @Override
		// public Render<EntitySimpleShotgunAmmo> createRenderFor(RenderManager manager)
		// {
		// return new RenderSimpleShotgunAmmo(manager);
		// }
		// });
	}
}