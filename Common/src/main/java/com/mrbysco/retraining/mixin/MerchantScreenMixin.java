package com.mrbysco.retraining.mixin;

import com.mrbysco.retraining.CommonRetraining;
import com.mrbysco.retraining.platform.Services;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MerchantMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MerchantScreen.class)
public abstract class MerchantScreenMixin extends AbstractContainerScreen<MerchantMenu> {

	public MerchantScreenMixin(MerchantMenu container, Inventory playerInventory, Component textComponent) {
		super(container, playerInventory, textComponent);
	}

	@Inject(at = @At("TAIL"), method = "init()V")
	public void retrainingInit(CallbackInfo ci) {
		if(CommonRetraining.isVillager) {
			this.addRenderableWidget(new Button(width / 2 + 112, height / 2 - 78, 20, 20, new TextComponent("\u27f3"), (button) -> {
				Services.PLATFORM.sendResetTradesMessage();
			}));
		}
	}
}
