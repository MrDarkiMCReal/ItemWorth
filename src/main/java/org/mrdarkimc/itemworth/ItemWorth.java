package org.mrdarkimc.itemworth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.itemworth.WorthProvider.PriceChecker;

import java.util.Arrays;
import java.util.Objects;

public final class ItemWorth extends JavaPlugin {
    private static ItemWorth instance;

    public static ItemWorth getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        //getServer().getPluginManager().registerEvents(new ItemChangeAmmountListener(), this);
        instance = this;
        getServer().getPluginCommand("worth").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PriceChecker priceChecker = new PriceChecker();
        Player player = ((Player) sender);
        int price = 0;
        switch (args.length) {
            case 0:
                int i = priceChecker.checkSellPrice(((Player) sender).getInventory().getItemInMainHand());
                player.sendMessage("Price: " + i);
                return true;
            case 1:
                for (ItemStack content : Arrays.stream(((Player) sender).getInventory().getContents()).filter(Objects::nonNull).toList()) {
                    price = price + priceChecker.checkSellPrice(content);
                }
                player.sendMessage("Price: " + price);
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
