package com.mrbysco.retraining.mixin;

import com.mrbysco.retraining.platform.Services;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MerchantMenu.class)
public abstract class MerchantMenuMixin extends AbstractContainerMenu {

	protected MerchantMenuMixin(MenuType<?> containerType, int menuID) {
		super(containerType, menuID);
	}

	@Inject(at = @At("TAIL"), method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/item/trading/Merchant;)V")
	public void retrainingConstructor(int id, Inventory playerInventory, Merchant merchant, CallbackInfo ci) {
		Player player = playerInventory.player;
		if (!player.level.isClientSide) {
			Services.PLATFORM.sendUpdateMessage(player, merchant instanceof Villager, merchant.getVillagerXp());
		}
	}
}
