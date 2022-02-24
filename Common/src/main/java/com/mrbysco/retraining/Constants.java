package com.mrbysco.retraining;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
	public static final String MOD_ID = "retraining";
	public static final String MOD_NAME = "Retraining";
	public static final Logger LOG = LogManager.getLogger(MOD_NAME);

	public static final ResourceLocation RESET_TRADES_PACKET_ID = new ResourceLocation(MOD_ID, "reset_trades");
	public static final ResourceLocation UPDATE_PACKET_ID = new ResourceLocation(MOD_ID, "update");
}