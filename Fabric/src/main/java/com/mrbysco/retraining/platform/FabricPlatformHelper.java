package com.mrbysco.retraining.platform;

import com.mrbysco.retraining.Constants;
import com.mrbysco.retraining.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class FabricPlatformHelper implements IPlatformHelper {

	@Override
	public void sendResetTradesMessage() {
		FriendlyByteBuf buf = PacketByteBufs.create();
		net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.send(Constants.RESET_TRADES_PACKET_ID, buf);
	}

	@Override
	public void sendUpdateMessage(Player player, boolean villager, int experience) {
		FriendlyByteBuf buf = PacketByteBufs.create();
		buf.writeBoolean(villager);
		buf.writeInt(experience);
		net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.send((net.minecraft.server.level.ServerPlayer) player, Constants.UPDATE_PACKET_ID, buf);
	}
}
