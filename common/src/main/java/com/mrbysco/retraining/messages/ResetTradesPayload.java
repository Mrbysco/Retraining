package com.mrbysco.retraining.messages;

import com.mrbysco.retraining.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ResetTradesPayload() implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, ResetTradesPayload> CODEC = CustomPacketPayload.codec(
			ResetTradesPayload::write,
			ResetTradesPayload::new);
	public static final Type<ResetTradesPayload> ID = CustomPacketPayload.createType(new ResourceLocation(Constants.MOD_ID, "reset_trades").toString());

	public ResetTradesPayload(final FriendlyByteBuf packetBuffer) {
		this();
	}

	public void write(FriendlyByteBuf buf) {
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}
}
