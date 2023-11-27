package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.CommonRetraining;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class ResetTradesMessage {

	public ResetTradesMessage() {
	}

	public void encode(FriendlyByteBuf buf) {
	}

	public static ResetTradesMessage decode(final FriendlyByteBuf packetBuffer) {
		return new ResetTradesMessage();
	}

	public void handle(Supplier<Context> context) {
		Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isServer() && ctx.getSender() != null) {
				ServerPlayer player = ctx.getSender();
				CommonRetraining.resetTrades(player);
			}
		});
		ctx.setPacketHandled(true);
	}
}
