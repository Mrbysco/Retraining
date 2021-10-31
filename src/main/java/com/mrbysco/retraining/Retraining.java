package com.mrbysco.retraining;

import com.mrbysco.retraining.network.PacketHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Retraining.MOD_ID)
public class Retraining {
    public static final String MOD_ID = "retraining";
    private static final Logger LOGGER = LogManager.getLogger();

    public Retraining() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
    }
}
