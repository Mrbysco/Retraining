package com.mrbysco.retraining.platform;

import com.mrbysco.retraining.network.PacketHandler;
import com.mrbysco.retraining.network.messages.ResetTradesMessage;
import com.mrbysco.retraining.network.messages.UpdateMessage;
import com.mrbysco.retraining.platform.services.IPlatformHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.network.PacketDistributor;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public void sendResetTradesMessage() {
        PacketHandler.CHANNEL.sendToServer(new ResetTradesMessage());
    }

    @Override
    public void sendUpdateMessage(Player player, boolean villager, int experience) {
        PacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new UpdateMessage(villager, experience));
    }
}
