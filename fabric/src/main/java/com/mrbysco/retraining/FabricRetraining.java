package com.mrbysco.retraining;

import com.mrbysco.retraining.config.RetrainingConfig;
import com.mrbysco.retraining.messages.ResetTradesPayload;
import com.mrbysco.retraining.messages.UpdatePayload;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.neoforged.fml.config.ModConfig;

public class FabricRetraining implements ModInitializer {
	@Override
	public void onInitialize() {
		ConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, RetrainingConfig.commonSpec);
		CommonRetraining.init();

		PayloadTypeRegistry.clientboundPlay().register(UpdatePayload.ID, UpdatePayload.CODEC);
		PayloadTypeRegistry.serverboundPlay().register(ResetTradesPayload.ID, ResetTradesPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(ResetTradesPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				CommonRetraining.resetTrades(context.player());
			});
		});
	}
}
