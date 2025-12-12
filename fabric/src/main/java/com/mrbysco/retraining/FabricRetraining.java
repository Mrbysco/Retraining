package com.mrbysco.retraining;

import com.mrbysco.retraining.config.RetrainingConfig;
import com.mrbysco.retraining.messages.ResetTradesPayload;
import com.mrbysco.retraining.messages.UpdatePayload;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class FabricRetraining implements ModInitializer {
	public static ConfigHolder<RetrainingConfig> config;

	@Override
	public void onInitialize() {
		config = AutoConfig.register(RetrainingConfig.class, Toml4jConfigSerializer::new);
		CommonRetraining.init();

		PayloadTypeRegistry.playS2C().register(UpdatePayload.ID, UpdatePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(ResetTradesPayload.ID, ResetTradesPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(ResetTradesPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				CommonRetraining.resetTrades(context.player());
			});
		});
	}
}
