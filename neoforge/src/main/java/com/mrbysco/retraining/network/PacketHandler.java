package com.mrbysco.retraining.network;

import com.mrbysco.retraining.Constants;
import com.mrbysco.retraining.network.handler.ClientPayloadHandler;
import com.mrbysco.retraining.network.handler.ServerPayloadHandler;
import com.mrbysco.retraining.network.messages.ResetTradesPayload;
import com.mrbysco.retraining.network.messages.UpdatePayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

public class PacketHandler {
	public static void setupPackets(final RegisterPayloadHandlerEvent event) {
		final IPayloadRegistrar registrar = event.registrar(Constants.MOD_ID);

		registrar.play(UpdatePayload.ID, UpdatePayload::new, handler -> handler
				.client(ClientPayloadHandler.getInstance()::handleData));
		registrar.play(ResetTradesPayload.ID, ResetTradesPayload::new, handler -> handler
				.server(ServerPayloadHandler.getInstance()::handleData));
	}
}
