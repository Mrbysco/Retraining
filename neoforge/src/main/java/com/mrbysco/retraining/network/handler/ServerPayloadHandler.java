package com.mrbysco.retraining.network.handler;

import com.mrbysco.retraining.CommonRetraining;
import com.mrbysco.retraining.messages.ResetTradesPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {
	public static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();

	public static ServerPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final ResetTradesPayload data, final IPayloadContext context) {
		context.enqueueWork(() -> {
					Player player = context.player();
					if (player instanceof ServerPlayer serverPlayer) {
						CommonRetraining.resetTrades(serverPlayer);
					}
				})
				.exceptionally(e -> {
					// Handle exception
					context.disconnect(Component.translatable("retraining.networking.reset_trades.failed", e.getMessage()));
					return null;
				});
	}
}
