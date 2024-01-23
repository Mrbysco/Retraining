package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UpdatePayload(boolean villager, int experience) implements CustomPacketPayload {
	public static final ResourceLocation ID = new ResourceLocation(Constants.MOD_ID, "update");

	public UpdatePayload(final FriendlyByteBuf packetBuffer) {
		this(packetBuffer.readBoolean(), packetBuffer.readInt());
	}

	public void write(FriendlyByteBuf buf) {
		buf.writeBoolean(villager);
		buf.writeInt(experience);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}
}
