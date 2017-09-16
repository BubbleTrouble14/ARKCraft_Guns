package com.bubble.gunmod.common.network;

import com.bubble.gunmod.common.item.IConsuming;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ReloadMessage implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

	}

	public static class ReloadMessageHandler implements IMessageHandler<ReloadMessage, IMessage> {
		@Override
		public IMessage onMessage(ReloadMessage message, MessageContext ctx) {
			if (ctx.side == Side.SERVER) {
				EntityPlayer player = ctx.getServerHandler().player;
				ItemStack stack = player.getHeldItemMainhand();
				if (stack.getItem() instanceof IConsuming) {
					IConsuming item = (IConsuming) stack.getItem();
					item.startReload(stack, player, player.world);
				}
			}
			return null;
		}
	}
}
