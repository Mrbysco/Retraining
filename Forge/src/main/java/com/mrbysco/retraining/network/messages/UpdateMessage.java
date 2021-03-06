package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.CommonRetraining;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class UpdateMessage {
	private final boolean villager;
	private final int experience;

	public UpdateMessage(boolean villager, int experience) {
		this.villager = villager;
		this.experience = experience;
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeBoolean(villager);
		buf.writeInt(experience);
	}

	public static UpdateMessage decode(final FriendlyByteBuf packetBuffer) {
		return new UpdateMessage(packetBuffer.readBoolean(), packetBuffer.readInt());
	}

	public void handle(Supplier<Context> context) {
		Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isClient()) {
				CommonRetraining.isVillager = experience == 0 && villager;
			}
		});
		ctx.setPacketHandled(true);
	}
}
