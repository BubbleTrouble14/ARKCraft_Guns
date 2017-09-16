package com.bubble.gunmod.common.item;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bubble.gunmod.common.item.attachment.AttachmentType;
import com.bubble.gunmod.common.item.attachment.IAttachment;
import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface ISupporting {
	public boolean supportsAttachment(AttachmentType type);

	// public int getAttachmentCount();

	public default Collection<AttachmentType> getSupportedAttachmentTypes() {
		List<AttachmentType> list = Lists.newArrayList(AttachmentType.values());
		Iterator<AttachmentType> it = list.iterator();
		while (it.hasNext()) {
			AttachmentType att = it.next();
			if (!supportsAttachment(att))
				it.remove();
		}
		return list;
	}

	public static AttachmentType getAttachmentType(ItemStack stack) {
		ItemStack att = getAttachment(stack);
		if (att != null && !att.isEmpty()) {
			return ((IAttachment) att.getItem()).getType();
		}
		return null;
	}

	public static ItemStack getAttachment(ItemStack itemStack) {
		return itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey("attachment")
				? new ItemStack(itemStack.getTagCompound().getCompoundTag("attachment"))
				: ItemStack.EMPTY;
	}

	public static void setAttachment(ItemStack itemStack, ItemStack attachment) {
		if (itemStack.getTagCompound() == null)
			itemStack.setTagCompound(new NBTTagCompound());

		NBTTagCompound c = itemStack.getTagCompound();
		c.setTag("attachment", attachment.serializeNBT());
	}
}
