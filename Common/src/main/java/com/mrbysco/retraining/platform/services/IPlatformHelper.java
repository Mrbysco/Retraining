package com.mrbysco.retraining.platform.services;

import net.minecraft.world.entity.player.Player;

public interface IPlatformHelper {

    /**
     * Send a packet to the server to reset the villager's trades
     */
    void sendResetTradesMessage();

    /**
     * Send a packet to the server to reset the villager's trades
     */
    void sendUpdateMessage(Player player, boolean villager, int experience);

}
