package com.mrbysco.retraining;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class FabricClientRetraining implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(Constants.UPDATE_PACKET_ID, (client, handler, buf, responseSender) -> {
			boolean villager = buf.readBoolean();
			int experience = buf.readInt();

			CommonRetraining.isVillager = experience == 0 && villager;
		});
	}
}
