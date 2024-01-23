package com.mrbysco.retraining.network.handler;

import com.mrbysco.retraining.CommonRetraining;
import com.mrbysco.retraining.network.messages.UpdatePayload;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class ClientPayloadHandler {
	private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

	public static ClientPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final UpdatePayload data, final PlayPayloadContext context) {
		context.workHandler().submitAsync(() -> {
					CommonRetraining.isVillager = data.experience() == 0 && data.villager();
				})
				.exceptionally(e -> {
					// Handle exception
					context.packetHandler().disconnect(Component.translatable("retraining.networking.update.failed", e.getMessage()));
					return null;
				});
	}
}
