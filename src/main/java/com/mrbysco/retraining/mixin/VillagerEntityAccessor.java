package com.mrbysco.retraining.mixin;

import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(VillagerEntity.class)
public interface VillagerEntityAccessor {
	@Invoker("updateTrades")
	void invokeUpdateTrades();

	@Invoker("updateSpecialPrices")
	void invokeUpdateSpecialPrices(PlayerEntity player);
}
