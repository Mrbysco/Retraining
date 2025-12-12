package com.mrbysco.retraining;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MOD_ID = "retraining";
	public static final String MOD_NAME = "Retraining";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static final Identifier RESET_TRADES_PACKET_ID = modLoc("reset_trades");
	public static final Identifier UPDATE_PACKET_ID = modLoc("update");

	private static Identifier modLoc(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}