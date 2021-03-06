package com.mrbysco.retraining.network;

import com.mrbysco.retraining.Constants;
import com.mrbysco.retraining.network.messages.ResetTradesMessage;
import com.mrbysco.retraining.network.messages.UpdateMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Constants.MOD_ID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);

	private static int id = 0;

	public static void init() {
		CHANNEL.registerMessage(id++, ResetTradesMessage.class, ResetTradesMessage::encode, ResetTradesMessage::decode, ResetTradesMessage::handle);
		CHANNEL.registerMessage(id++, UpdateMessage.class, UpdateMessage::encode, UpdateMessage::decode, UpdateMessage::handle);
	}
}
