package com.bubble.gunmod.client;

import org.lwjgl.input.Keyboard;

import com.bubble.gunmod.Main;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {
	public static KeyBinding attachment, reload;

	public static void init() {
		attachment = new KeyBinding("key.gunmod.attachment", Keyboard.KEY_M, Main.MODID);
		ClientRegistry.registerKeyBinding(attachment);
		reload = new KeyBinding("key.gunmod.reload", Keyboard.KEY_R, Main.MODID);
		ClientRegistry.registerKeyBinding(reload);
	}
}
