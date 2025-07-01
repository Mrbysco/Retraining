package com.mrbysco.retraining.platform;

import com.mrbysco.retraining.messages.ResetTradesPayload;
import com.mrbysco.retraining.messages.UpdatePayload;
import com.mrbysco.retraining.platform.services.IPlatformHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class NeoForgePlatformHelper implements IPlatformHelper {

	@Override
	public void sendResetTradesMessage() {
		net.neoforged.neoforge.client.network.ClientPacketDistributor.sendToServer(new ResetTradesPayload());
	}

	@Override
	public void sendUpdateMessage(Player player, boolean villager, int experience) {
		((ServerPlayer) player).connection.send(new UpdatePayload(villager, experience));
	}
}
