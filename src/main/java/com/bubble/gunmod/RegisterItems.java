package com.bubble.gunmod;

import com.bubble.gunmod.a.EntityProjectile;
import com.bubble.gunmod.a.ItemAmmunition;
import com.bubble.gunmod.common.entity.EntityAdvancedBullet;
import com.bubble.gunmod.common.entity.EntitySimpleBullet;
import com.bubble.gunmod.common.entity.EntitySimpleRifleAmmo;
import com.bubble.gunmod.common.entity.EntitySimpleShotgunAmmo;
import com.bubble.gunmod.item.ItemFabricatedPistol;
import com.bubble.gunmod.item.ItemLongneckRifle;
import com.bubble.gunmod.item.ItemShotgun;
import com.bubble.gunmod.item.ItemSimplePistol;
import com.bubble.gunmod.item.attachment.ItemFlashlight;
import com.bubble.gunmod.item.attachment.ItemLaser;
import com.bubble.gunmod.item.attachment.ItemScope;
import com.bubble.gunmod.item.attachment.ItemSilencer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterItems {
	public static ItemAmmunition simple_bullet, simple_rifle_ammo, simple_shotgun_ammo, advanced_bullet;
	public static Item scope, flashlight, laser, silencer;
	public static Item simple_pistol, fabricated_pistol, longneck_rifle, shotgun;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		simple_pistol = new ItemSimplePistol("simple_pistol");
		longneck_rifle = new ItemLongneckRifle("longneck_rifle");
		shotgun = new ItemShotgun("shotgun");
		fabricated_pistol = new ItemFabricatedPistol("fabricated_pistol");

		event.getRegistry().register(simple_pistol);
		event.getRegistry().register(longneck_rifle);
		event.getRegistry().register(shotgun);
		event.getRegistry().register(fabricated_pistol);

		// Ammo
		simple_bullet = new ItemAmmunition("simple_bullet") {
			@Override
			public EntityProjectile createProjectile(EntityPlayer player) {
				return new EntitySimpleBullet(player.world, player, 5, 0, 5, 20);
			}
		};
		simple_rifle_ammo = new ItemAmmunition("simple_rifle_ammo") {
			@Override
			public EntityProjectile createProjectile(EntityPlayer player) {
				return new EntitySimpleRifleAmmo(player.world, player, 5, 0, 5, 20);
			}
		};
		simple_shotgun_ammo = new ItemAmmunition("simple_shotgun_ammo") {
			@Override
			public EntityProjectile createProjectile(EntityPlayer player) {
				return new EntitySimpleShotgunAmmo(player.world, player, 5, 0, 5, 20);
			}
		};
		advanced_bullet = new ItemAmmunition("advanced_bullet") {
			@Override
			public EntityProjectile createProjectile(EntityPlayer player) {
				return new EntityAdvancedBullet(player.world, player, 5, 0, 5, 20);
			}
		};

		event.getRegistry().register(simple_bullet);
		event.getRegistry().register(simple_rifle_ammo);
		event.getRegistry().register(simple_shotgun_ammo);
		event.getRegistry().register(advanced_bullet);

		// Attachments
		scope = new ItemScope("scope");
		flashlight = new ItemFlashlight("flashlight");
		laser = new ItemLaser("laser");
		silencer = new ItemSilencer("silencer");

		event.getRegistry().register(scope);
		event.getRegistry().register(flashlight);
		event.getRegistry().register(laser);
		event.getRegistry().register(silencer);

	}
}
