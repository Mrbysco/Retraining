package com.mrbysco.retraining.platform;

import com.mrbysco.retraining.network.PacketHandler;
import com.mrbysco.retraining.network.messages.ResetTradesMessage;
import com.mrbysco.retraining.network.messages.UpdateMessage;
import com.mrbysco.retraining.platform.services.IPlatformHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgePlatformHelper implements IPlatformHelper {

	@Override
	public void sendResetTradesMessage() {
		PacketHandler.CHANNEL.sendToServer(new ResetTradesMessage());
	}

	@Override
	public void sendUpdateMessage(Player player, boolean villager, int experience) {
		PacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new UpdateMessage(villager, experience));
	}
}
