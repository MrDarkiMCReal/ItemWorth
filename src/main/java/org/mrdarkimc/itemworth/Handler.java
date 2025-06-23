package org.mrdarkimc.itemworth;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mrdarkimc.itemworth.PacketProvider.PacketProvider;
import org.mrdarkimc.itemworth.PacketProvider.ProtocolLibProvider;

import java.util.*;

public class Handler {
    public static Handler instance;

    public static Handler getInstance() {
        return instance;
    }

    PacketProvider packetProvider = new ProtocolLibProvider();
    private Map<Material, Long> worths;

    {
        worths = new HashMap<>();
        worths.put(Material.STONE, 10L);
        worths.put(Material.DIAMOND_CHESTPLATE, 50L);
        worths.put(Material.NETHERITE_SWORD, 40L);
        worths.put(Material.COBBLESTONE, 5L);
        instance = this;
    }

    public void updateStack(Player player, ItemStack stack, int slotID) {
        System.out.println("calling update");
        ItemStack fakeItem = stack.clone(); // безопасная копия предмета
        ItemMeta meta = fakeItem.getItemMeta(); // получаем ItemMeta уже из клона

        List<String> lore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
        lore.removeIf(line -> line.startsWith("worth"));
        lore.add("worth: " + getWorth(stack));
        meta.setLore(lore);

        fakeItem.setItemMeta(meta); // теперь безопасно
        Bukkit.getScheduler().runTaskLater(ItemWorth.getInstance(), () -> {
            packetProvider.sendWorthPacket(player, fakeItem, slotID);
        }, 1L);
        //packetProvider.sendWorthPacket(player, fakeItem, slotID);
}

    public long getWorth(ItemStack stack) {
        return worths.getOrDefault(stack.getType(), 15L) * stack.getAmount();
    }
}