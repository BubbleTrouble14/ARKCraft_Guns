package com.bubble.gunmod.common.item;

import static com.bubble.gunmod.common.item.IConsuming.isReloading;

import com.bubble.gunmod.common.item.attachment.AttachmentType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemRangedWeapon extends ItemRangedWeaponBase implements ISupporting {
	public ItemRangedWeapon(String name, int durability) {
		super(name, durability);
	}

	@Override
	protected void registerPropertyOverrides() {
		addPropertyOverride(new ResourceLocation("reload_attachment"), new IItemPropertyGetter() {
			@Override
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				int out = 0;
				if (isReloading(stack)) {
					//TODOD return 1
					out |= 0;
				}
				AttachmentType at = ISupporting.getAttachmentType(stack);
				if (at != null)
					for (AttachmentType type : getSupportedAttachmentTypes()) {
						if (type == at) {
							out |= type.getId() << 1;
						}
					}
				return out;
			}
		});
	}
}
