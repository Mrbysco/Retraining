package com.mrbysco.retraining;

import com.mrbysco.retraining.config.RetrainingConfig;
import com.mrbysco.retraining.messages.UpdatePayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class FabricClientRetraining implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(UpdatePayload.ID, (payload, context) -> {
			boolean villager = payload.villager();
			int experience = payload.experience();
			CommonRetraining.isVillager = (RetrainingConfig.COMMON.ignoreExperience.get() || experience == 0) && villager;
		});
	}
}
