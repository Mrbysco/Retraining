package com.mrbysco.retraining.platform;

import com.mrbysco.retraining.network.messages.ResetTradesPayload;
import com.mrbysco.retraining.network.messages.UpdatePayload;
import com.mrbysco.retraining.platform.services.IPlatformHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgePlatformHelper implements IPlatformHelper {

	@Override
	public void sendResetTradesMessage() {
		PacketDistributor.SERVER.noArg().send(new ResetTradesPayload());
	}

	@Override
	public void sendUpdateMessage(Player player, boolean villager, int experience) {
		((ServerPlayer) player).connection.send(new UpdatePayload(villager, experience));
	}
}
