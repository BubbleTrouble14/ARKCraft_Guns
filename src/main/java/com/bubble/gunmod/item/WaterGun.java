package com.bubble.gunmod.item;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.RegisterItems;
import com.bubble.gunmod.a.AttachmentType;
import com.bubble.gunmod.a.ItemAmmunition;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WaterGun extends ItemRangedWeapon {

	public WaterGun(String name) {
		super(name);
	}

	@SideOnly(Side.CLIENT)
	public void initModels() {
		System.out.println("INIT MODEL");
		ModelResourceLocation scope = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_scope", "inventory");
		ModelResourceLocation scopereload = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_scope_reload", "inventory");
		ModelResourceLocation laser = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_laser", "inventory");
		ModelResourceLocation laserreload = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_laser_reload", "inventory");
		ModelResourceLocation silencer = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_silencer", "inventory");
		ModelResourceLocation silencerreload = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_silencer_reload", "inventory");
		ModelResourceLocation flashlight = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_flashlight", "inventory");
		ModelResourceLocation flashlightreload = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_flashlight_reload", "inventory");
		ModelResourceLocation reload = new ModelResourceLocation(
				Main.MODID + ":weapons/" + this.getUnlocalizedName() + "_reload", "inventory");
		ModelResourceLocation normal = new ModelResourceLocation(Main.MODID + ":weapons/" + this.getUnlocalizedName(),
				"inventory");

		ModelBakery.registerItemVariants(this, scope, laser, silencer, flashlight, reload, normal, scopereload,
				laserreload, silencerreload, flashlightreload);
	}

	@Override
	public boolean isValidAmmunition(ItemAmmunition item) {
		return item == RegisterItems.advanced_bullet;
	}

	@Override
	public boolean supportsAttachment(AttachmentType type) {
		return type == AttachmentType.SCOPE;
	}

	@Override
	public int getAmmunitionCapacity() {
		return 4;
	}

	@Override
	public int getReloadingTime() {
		return 40;
	}

}
