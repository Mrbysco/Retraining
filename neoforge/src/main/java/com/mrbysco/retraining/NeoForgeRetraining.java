package com.mrbysco.retraining;

import com.mrbysco.retraining.network.PacketHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NeoForgeRetraining {

	public NeoForgeRetraining(IEventBus eventBus) {
		CommonRetraining.init();

		eventBus.addListener(PacketHandler::setupPackets);
	}
}