package com.mrbysco.retraining;

import com.mrbysco.retraining.config.RetrainingConfig;
import com.mrbysco.retraining.network.PacketHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Constants.MOD_ID)
public class NeoForgeRetraining {

	public NeoForgeRetraining(IEventBus eventBus, ModContainer container, Dist dist) {
		container.registerConfig(ModConfig.Type.COMMON, RetrainingConfig.commonSpec);
		eventBus.register(RetrainingConfig.class);

		CommonRetraining.init();

		eventBus.addListener(PacketHandler::setupPackets);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
	}
}