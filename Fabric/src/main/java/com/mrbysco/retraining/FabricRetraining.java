package com.mrbysco.retraining;

import com.mrbysco.retraining.mixin.AbstractVillagerEntityAccessor;
import com.mrbysco.retraining.mixin.MerchantMenuAccessor;
import com.mrbysco.retraining.mixin.VillagerAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffers;

public class FabricRetraining implements ModInitializer {

	@Override
	public void onInitialize() {
		CommonRetraining.init();

		ServerPlayNetworking.registerGlobalReceiver(Constants.RESET_TRADES_PACKET_ID, (server, player, handler, buf, responseSender) -> {
			server.execute(() -> {
				AbstractContainerMenu container = player.containerMenu;
				if (container instanceof MerchantMenu) {
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
			});
		});
	}
}
