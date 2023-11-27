package com.mrbysco.retraining;

import com.mrbysco.retraining.mixin.AbstractVillagerEntityAccessor;
import com.mrbysco.retraining.mixin.MerchantMenuAccessor;
import com.mrbysco.retraining.mixin.VillagerAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffers;

public class CommonRetraining {
	public static boolean isVillager = false;

	public static void init() {

	}

	public static void resetTrades(ServerPlayer player) {
		AbstractContainerMenu container = player.containerMenu;
		if (container instanceof MerchantMenu merchantMenu && !merchantMenu.getSlot(2).hasItem()) {
			Merchant merchant = ((MerchantMenuAccessor) container).getTrader();
			if (merchant instanceof Villager villager) {
				if (villager.getVillagerXp() == 0) {
					MerchantOffers newOffers = new MerchantOffers();
					((AbstractVillagerEntityAccessor) villager).setOffers(newOffers);
					((VillagerAccessor) villager).invokeUpdateTrades();
					((VillagerAccessor) villager).invokeUpdateSpecialPrices(player);
					player.sendMerchantOffers(container.containerId, newOffers, villager.getVillagerData().getLevel(),
							villager.getVillagerXp(), villager.showProgressBar(), villager.canRestock());
				}
			}
		}
	}
}