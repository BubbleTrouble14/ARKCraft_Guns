package com.bubble.gunmod.common.network;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.common.item.ISupporting;
import com.bubble.gunmod.proxy.CommonProxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class OpenAttachmentInventory implements IMessage {
	public OpenAttachmentInventory() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<OpenAttachmentInventory, IMessage> {
		@Override
		public IMessage onMessage(final OpenAttachmentInventory message, MessageContext ctx) {
			if (ctx.side != Side.SERVER) {
				System.err.println("MPUpdateDoAttachment received on wrong side:" + ctx.side);
				return null;
			}
			final EntityPlayerMP player = ctx.getServerHandler().player;
			player.getServer().addScheduledTask(new Runnable() {
				public void run() {
					processMessage(message, player);
				}
			});
			return null;
		}
	}

	// On Server
	static void processMessage(OpenAttachmentInventory message, EntityPlayerMP player) {
		if (player != null) {
			if (player.getHeldItemMainhand().getItem() instanceof ISupporting) {
				player.openGui(Main.instance(), CommonProxy.GUI.ATTACHMENTS.id, player.world, 0, 0, 0);
			}
		}
	}
}
