package com.mrbysco.retraining;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class FabricRetraining implements ModInitializer {

	@Override
	public void onInitialize() {
		CommonRetraining.init();

		ServerPlayNetworking.registerGlobalReceiver(Constants.RESET_TRADES_PACKET_ID, (server, player, handler, buf, responseSender) -> {
			server.execute(() -> {
				CommonRetraining.resetTrades(player);
			});
		});
	}
}
