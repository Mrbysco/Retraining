package com.mrbysco.retraining;

import com.mrbysco.retraining.messages.ResetTradesPayload;
import com.mrbysco.retraining.messages.UpdatePayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class FabricRetraining implements ModInitializer {

	@Override
	public void onInitialize() {
		CommonRetraining.init();

		PayloadTypeRegistry.playS2C().register(UpdatePayload.ID, UpdatePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(ResetTradesPayload.ID, ResetTradesPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(ResetTradesPayload.ID, (payload, context) -> {
			context.player().server.execute(() -> {
				CommonRetraining.resetTrades(context.player());
			});
		});
	}
}
