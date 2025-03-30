package com.mrbysco.retraining.messages;

import com.mrbysco.retraining.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record UpdatePayload(boolean villager, int experience) implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, UpdatePayload> CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL,
			(payload) -> payload.villager,
			ByteBufCodecs.INT,
			(payload) -> payload.experience,
			UpdatePayload::new
	);
	public static final Type<UpdatePayload> ID = new Type<>(Constants.UPDATE_PACKET_ID);

	@Override
	@NotNull
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}
}
