package com.mrbysco.retraining;

import com.mrbysco.retraining.network.PacketHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class NeoForgeRetraining {

	public NeoForgeRetraining() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		CommonRetraining.init();

		eventBus.addListener(this::setup);
	}

	private void setup(final FMLCommonSetupEvent event) {
		PacketHandler.init();
	}
}