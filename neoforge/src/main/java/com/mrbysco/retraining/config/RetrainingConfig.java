package com.mrbysco.retraining.config;

import com.mrbysco.retraining.Constants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class RetrainingConfig {

	public static class Common {
		public final ModConfigSpec.BooleanValue ignoreExperience;

		Common(ModConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("general");

			ignoreExperience = builder
					.comment("Ignore the experience check on villagers (Not recommended) [default: false]")
					.define("ignoreExperience", false);

			builder.pop();
		}
	}

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		Constants.LOGGER.debug("Loaded Retraining's config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		Constants.LOGGER.warn("Retraining's config just got changed on the file system!");
	}
}
