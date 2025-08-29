package com.mrbysco.retraining.platform;

import com.mrbysco.retraining.FabricRetraining;
import com.mrbysco.retraining.messages.ResetTradesPayload;
import com.mrbysco.retraining.messages.UpdatePayload;
import com.mrbysco.retraining.platform.services.IPlatformHelper;
import net.minecraft.world.entity.player.Player;

public class FabricPlatformHelper implements IPlatformHelper {

	@Override
	public void sendResetTradesMessage() {
		net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.send(new ResetTradesPayload());
	}

	@Override
	public void sendUpdateMessage(Player player, boolean villager, int experience) {
		net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.send((net.minecraft.server.level.ServerPlayer) player, new UpdatePayload(villager, experience));
	}

	@Override
	public boolean ignoreExperienceCheck() {
		return FabricRetraining.config.get().general.ignoreExperience;
	}
}
