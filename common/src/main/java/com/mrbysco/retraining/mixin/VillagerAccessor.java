package com.mrbysco.retraining.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Villager.class)
public interface VillagerAccessor {
	@Invoker("updateTrades")
	void invokeUpdateTrades(ServerLevel serverLevel);

	@Invoker("updateSpecialPrices")
	void invokeUpdateSpecialPrices(Player player);
}
