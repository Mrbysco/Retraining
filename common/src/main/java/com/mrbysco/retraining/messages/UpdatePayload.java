package com.mrbysco.retraining.messages;

import com.mrbysco.retraining.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UpdatePayload(boolean villager, int experience) implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, UpdatePayload> CODEC = CustomPacketPayload.codec(
			UpdatePayload::write,
			UpdatePayload::new);
	public static final Type<UpdatePayload> ID = CustomPacketPayload.createType(new ResourceLocation(Constants.MOD_ID, "update").toString());

	public UpdatePayload(final FriendlyByteBuf packetBuffer) {
		this(packetBuffer.readBoolean(), packetBuffer.readInt());
	}

	public void write(FriendlyByteBuf buf) {
		buf.writeBoolean(villager);
		buf.writeInt(experience);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}
}
