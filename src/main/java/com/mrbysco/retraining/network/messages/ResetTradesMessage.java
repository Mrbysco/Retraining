package com.mrbysco.retraining.network.messages;

import com.mrbysco.retraining.mixin.AbstractVillagerEntityAccessor;
import com.mrbysco.retraining.mixin.MerchantContainerAccessor;
import com.mrbysco.retraining.mixin.VillagerEntityAccessor;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.MerchantContainer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class ResetTradesMessage {

	public ResetTradesMessage() {
	}

	public void encode(PacketBuffer buf) {
	}

	public static ResetTradesMessage decode(final PacketBuffer packetBuffer) {
		return new ResetTradesMessage();
	}

	public void handle(Supplier<Context> context) {
		NetworkEvent.Context ctx = context.get();
		ctx.enqueueWork(() -> {
			if (ctx.getDirection().getReceptionSide().isServer() && ctx.getSender() != null) {
				ServerPlayerEntity player = ctx.getSender();
				Container container = player.containerMenu;
				if (container instanceof MerchantContainer) {
					MerchantContainer merchantContainer = (MerchantContainer) container;
					if (!merchantContainer.getSlot(2).hasItem()) {
						IMerchant merchant = ((MerchantContainerAccessor) container).getTrader();
						if (merchant instanceof VillagerEntity) {
							VillagerEntity villager = (VillagerEntity) merchant;
							if (villager.getVillagerXp() == 0) {
								MerchantOffers newOffers = new MerchantOffers();
								((AbstractVillagerEntityAccessor) villager).setOffers(newOffers);
								((VillagerEntityAccessor) villager).invokeUpdateTrades();
								((VillagerEntityAccessor) villager).invokeUpdateSpecialPrices(player);
								player.sendMerchantOffers(player.containerCounter, newOffers, villager.getVillagerData().getLevel(),
										villager.getVillagerXp(), villager.showProgressBar(), villager.canRestock());
							}
						}
					}
				}
			}
		});
		ctx.setPacketHandled(true);
	}
}
