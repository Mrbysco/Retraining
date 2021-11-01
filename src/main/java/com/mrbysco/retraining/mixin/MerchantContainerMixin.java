package com.mrbysco.retraining.mixin;

import com.mrbysco.retraining.network.PacketHandler;
import com.mrbysco.retraining.network.messages.UpdateMessage;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.MerchantContainer;
import net.minecraftforge.fml.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(MerchantContainer.class)
public abstract class MerchantContainerMixin extends Container {

	protected MerchantContainerMixin(@Nullable ContainerType<?> containerType, int menuID) {
		super(containerType, menuID);
	}

	@Inject(at = @At("TAIL"), method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/entity/merchant/IMerchant;)V")
	public void retrainingConstructor(int id, PlayerInventory playerInventory, IMerchant merchant, CallbackInfo ci) {
		PlayerEntity player = playerInventory.player;
		if(!player.level.isClientSide) {
			PacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)playerInventory.player), new UpdateMessage(merchant instanceof VillagerEntity));
		}
	}
}
