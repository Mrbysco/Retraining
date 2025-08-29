package com.mrbysco.retraining.network.handler;

import com.mrbysco.retraining.CommonRetraining;
import com.mrbysco.retraining.config.RetrainingConfig;
import com.mrbysco.retraining.messages.UpdatePayload;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
	private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

	public static ClientPayloadHandler getInstance() {
		return INSTANCE;
	}

	public void handleData(final UpdatePayload data, final IPayloadContext context) {
		context.enqueueWork(() -> {
					CommonRetraining.isVillager = (RetrainingConfig.COMMON.ignoreExperience.getAsBoolean() || data.experience() == 0) && data.villager();
				})
				.exceptionally(e -> {
					// Handle exception
					context.disconnect(Component.translatable("retraining.networking.update.failed", e.getMessage()));
					return null;
				});
	}
}
