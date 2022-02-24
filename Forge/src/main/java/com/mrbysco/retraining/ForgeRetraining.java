package com.mrbysco.retraining;

import com.mrbysco.retraining.network.PacketHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ForgeRetraining {
    
    public ForgeRetraining() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CommonRetraining.init();

        eventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
    }
}