package com.mrbysco.retraining.network;

import com.mrbysco.retraining.Constants;
import com.mrbysco.retraining.messages.ResetTradesPayload;
import com.mrbysco.retraining.messages.UpdatePayload;
import com.mrbysco.retraining.network.handler.ClientPayloadHandler;
import com.mrbysco.retraining.network.handler.ServerPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketHandler {
	public static void setupPackets(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(Constants.MOD_ID);

		registrar.playToClient(UpdatePayload.ID, UpdatePayload.CODEC, ClientPayloadHandler.getInstance()::handleData);
		registrar.playToServer(ResetTradesPayload.ID, ResetTradesPayload.CODEC, ServerPayloadHandler.getInstance()::handleData);
	}
}
