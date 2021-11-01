package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.Retraining;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class UpdateMessage {
	private final boolean villager;
	public UpdateMessage(boolean villager) {
		this.villager = villager;
	}

	public void encode(PacketBuffer buf) {
		buf.writeBoolean(villager);
	}

	public static UpdateMessage decode(final PacketBuffer packetBuffer) {
		return new UpdateMessage(packetBuffer.readBoolean());
	}

	public void handle(Supplier<Context> context) {
		Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isClient()) {
				Retraining.isVillager = villager;
			}
		});
		ctx.setPacketHandled(true);
	}
}
