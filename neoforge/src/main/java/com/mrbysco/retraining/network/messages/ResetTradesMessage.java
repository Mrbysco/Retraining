package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.CommonRetraining;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.NetworkEvent;

public class ResetTradesMessage {

	public ResetTradesMessage() {
	}

	public void encode(FriendlyByteBuf buf) {
	}

	public static ResetTradesMessage decode(final FriendlyByteBuf packetBuffer) {
		return new ResetTradesMessage();
	}

	public void handle(NetworkEvent.Context ctx) {
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isServer() && ctx.getSender() != null) {
				ServerPlayer player = ctx.getSender();
				CommonRetraining.resetTrades(player);
			}
		});
		ctx.setPacketHandled(true);
	}
}
