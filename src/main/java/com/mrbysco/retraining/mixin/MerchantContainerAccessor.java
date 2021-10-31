package com.mrbysco.retraining.mixin;

import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.inventory.container.MerchantContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MerchantContainer.class)
public interface MerchantContainerAccessor {
	@Accessor("trader")
	IMerchant getTrader();
}
