package org.mrdarkimc.itemworth.PacketProvider;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PacketProvider {
    void sendWorthPacket(Player player, ItemStack stack, int slot);
}
