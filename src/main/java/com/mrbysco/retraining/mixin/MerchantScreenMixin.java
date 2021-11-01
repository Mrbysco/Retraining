package com.mrbysco.retraining.mixin;

import com.mrbysco.retraining.Retraining;
import com.mrbysco.retraining.network.PacketHandler;
import com.mrbysco.retraining.network.messages.ResetTradesMessage;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.MerchantContainer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MerchantScreen.class)
public abstract class MerchantScreenMixin extends ContainerScreen<MerchantContainer> {

	public MerchantScreenMixin(MerchantContainer container, PlayerInventory playerInventory, ITextComponent textComponent) {
		super(container, playerInventory, textComponent);
	}

	@Inject(at = @At("TAIL"), method = "init()V")
	public void retrainingInit(CallbackInfo ci) {
		if(Retraining.isVillager) {
			this.addButton(new Button(width / 2 + 112, height / 2 - 78, 20, 20, new StringTextComponent("\u27f3"), (button) -> {
				PacketHandler.CHANNEL.sendToServer(new ResetTradesMessage());
			}));
		}
	}
}
