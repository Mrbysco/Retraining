package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.mixin.AbstractVillagerEntityAccessor;
import com.mrbysco.retraining.mixin.MerchantMenuAccessor;
import com.mrbysco.retraining.mixin.VillagerAccessor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class ResetTradesMessage {

	public ResetTradesMessage() {
	}

	public void encode(FriendlyByteBuf buf) {
	}

	public static ResetTradesMessage decode(final FriendlyByteBuf packetBuffer) {
		return new ResetTradesMessage();
	}

	public void handle(Supplier<Context> context) {
		Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isServer() && ctx.getSender() != null) {
				ServerPlayer player = ctx.getSender();
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
			}
		});
		ctx.setPacketHandled(true);
	}
}
