package com.bubble.gunmod;

import java.util.ArrayList;
import java.util.Collection;

import com.bubble.gunmod.common.entity.EntityAdvancedBullet;
import com.bubble.gunmod.common.entity.EntityProjectile;
import com.bubble.gunmod.common.entity.EntitySimpleBullet;
import com.bubble.gunmod.common.entity.EntitySimpleRifleAmmo;
import com.bubble.gunmod.common.entity.EntitySimpleShotgunAmmo;
import com.bubble.gunmod.common.item.ItemAmmunition;
import com.bubble.gunmod.common.item.ItemFabricatedPistol;
import com.bubble.gunmod.common.item.ItemLongneckRifle;
import com.bubble.gunmod.common.item.ItemShotgun;
import com.bubble.gunmod.common.item.ItemSimplePistol;
import com.bubble.gunmod.common.item.attachment.ItemFlashlight;
import com.bubble.gunmod.common.item.attachment.ItemLaser;
import com.bubble.gunmod.common.item.attachment.ItemScope;
import com.bubble.gunmod.common.item.attachment.ItemSilencer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterItems {
    private static final Collection<Item> itemList = new ArrayList<>();
    public static ItemAmmunition simple_bullet, simple_rifle_ammo, simple_shotgun_ammo, advanced_bullet;
    public static Item scope, flashlight, laser, silencer;
    public static Item simple_pistol, fabricated_pistol, longneck_rifle, shotgun;

    public static Collection<Item> getItemlist() {
	return itemList;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	simple_pistol = registerItem(event, new ItemSimplePistol("simple_pistol"));
	longneck_rifle = registerItem(event, new ItemLongneckRifle("longneck_rifle"));
	shotgun = registerItem(event, new ItemShotgun("shotgun"));
	fabricated_pistol = registerItem(event, new ItemFabricatedPistol("fabricated_pistol"));

	// Ammo
	simple_bullet = registerItem(event, new ItemAmmunition("simple_bullet") {
	    @Override
	    public EntityProjectile createProjectile(EntityPlayer player) {
		return new EntitySimpleBullet(player.world, player, 5, 2.5f, 6, 20);
	    }
	});
	simple_rifle_ammo = registerItem(event, new ItemAmmunition("simple_rifle_ammo") {
	    @Override
	    public EntityProjectile createProjectile(EntityPlayer player) {
		return new EntitySimpleRifleAmmo(player.world, player, 7F, 0F, 16, 200);
	    }
	});
	simple_shotgun_ammo = registerItem(event, new ItemAmmunition("simple_shotgun_ammo") {
	    @Override
	    public EntityProjectile createProjectile(EntityPlayer player) {
		return new EntitySimpleShotgunAmmo(player.world, player, 6F, 15F, 14, 5);
	    }
	});
	advanced_bullet = registerItem(event, new ItemAmmunition("advanced_bullet") {
	    @Override
	    public EntityProjectile createProjectile(EntityPlayer player) {
		return new EntityAdvancedBullet(player.world, player, 6F, 1.4F, 12, 80);
	    }
	});

	// Attachments
	scope = registerItem(event, new ItemScope("scope"));
	flashlight = registerItem(event, new ItemFlashlight("flashlight"));
	laser = registerItem(event, new ItemLaser("laser"));
	silencer = registerItem(event, new ItemSilencer("silencer"));

	event.getRegistry().register(
		new ItemBlock(RegisterBlocks.crafting).setRegistryName(RegisterBlocks.crafting.getRegistryName()));
    }

    private static <E extends Item> E registerItem(RegistryEvent.Register<Item> event, E item) {
	itemList.add(item);
	event.getRegistry().register(item);
	return item;
    }
}
