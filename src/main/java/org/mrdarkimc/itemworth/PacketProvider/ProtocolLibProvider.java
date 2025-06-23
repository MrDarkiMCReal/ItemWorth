package org.mrdarkimc.itemworth.PacketProvider;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ProtocolLibProvider implements PacketProvider {
    public void sendWorthPacket(Player player, ItemStack stack, int slot){
        System.out.println("Sending packet");
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SET_SLOT);
        packet.getIntegers().write(0, 0);
        packet.getIntegers().write(1, slot); //max index 1
        packet.getItemModifier().write(0, stack); // max index 0
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    }
}
