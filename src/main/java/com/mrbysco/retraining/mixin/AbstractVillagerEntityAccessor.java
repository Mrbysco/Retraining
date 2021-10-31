package com.mrbysco.retraining.mixin;

import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.item.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractVillagerEntity.class)
public interface AbstractVillagerEntityAccessor {
	@Accessor("offers")
	void setOffers(MerchantOffers offer);
}
