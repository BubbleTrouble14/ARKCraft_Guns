package com.bubble.gunmod;

import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.EntityProjectile;
import com.bubble.gunmod.a.EntitySimpleBullet;
import com.bubble.gunmod.a.ItemAmmunition;
import com.bubble.gunmod.a.ItemAttachment;
import com.bubble.gunmod.item.WaterGun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegisterItems 
{
	public static Item item_test_ammo;
	public static Item item_test_attachment;
	public static Item item_test_gun;
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
		item_test_gun = new WaterGun(new ResourceLocation(Main.MODID, "test_gun"));
        event.getRegistry().register(item_test_gun);

		item_test_ammo = new ItemAmmunition(new ResourceLocation(Main.MODID, "test_ammo")) {
			@Override
			public EntityProjectile createProjectile(EntityPlayer player) {
				return new EntitySimpleBullet(player.world, player, 5, 0, 5, 20);
			}
		};
        event.getRegistry().register(item_test_ammo);

		item_test_attachment = new ItemAttachment(
				new ResourceLocation(Main.MODID, "test_attachment")) {

			@Override
			public void runUseEffect(World world, EntityPlayer player) {
				// TODO Auto-generated method stub

			}

			@Override
			public void runEffect(World world, EntityPlayer player) {
				// TODO Auto-generated method stub

			}

			@Override
			@SideOnly(Side.CLIENT)
			public void runClientSideUseEffect(World world, EntityPlayer player) {
				// TODO Auto-generated method stub

			}

			@Override
			@SideOnly(Side.CLIENT)
			public void runClientSideEffect(World world, EntityPlayer player) {
				// TODO Auto-generated method stub

			}

			@Override
			public AttachmentType getType() {
				return AttachmentType.SCOPE;
			}
		};
        event.getRegistry().register(item_test_attachment);
    }
}
