package com.mrbysco.retraining.network.handler;

import com.mrbysco.retraining.CommonRetraining;
import com.mrbysco.retraining.network.messages.ResetTradesPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class ServerPayloadHandler {
	public static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();

	public static ServerPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final ResetTradesPayload data, final PlayPayloadContext context) {
		// Do something with the data, on the main thread
		context.workHandler().submitAsync(() -> {
					//Sync big Player Statue data
					if (context.player().isPresent()) {
						if (context.player().get() instanceof ServerPlayer serverPlayer) {
							CommonRetraining.resetTrades(serverPlayer);
						}
					}
				})
				.exceptionally(e -> {
					// Handle exception
					context.packetHandler().disconnect(Component.translatable("retraining.networking.reset_trades.failed", e.getMessage()));
					return null;
				});
	}
}
