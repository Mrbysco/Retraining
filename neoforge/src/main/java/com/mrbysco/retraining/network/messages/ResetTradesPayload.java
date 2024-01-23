package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ResetTradesPayload() implements CustomPacketPayload {
	public static final ResourceLocation ID = new ResourceLocation(Constants.MOD_ID, "reset_trades");

	public ResetTradesPayload(final FriendlyByteBuf packetBuffer) {
		this();
	}

	public void write(FriendlyByteBuf buf) {
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}
}
