package org.mrdarkimc.itemworth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.SatanicLib;
import org.mrdarkimc.SatanicLib.configsetups.Configs;
import org.mrdarkimc.SatanicLib.messages.Message;
import org.mrdarkimc.itemworth.WorthProvider.PriceChecker;
import org.mrdarkimc.itemworth.WorthProvider.PriceList;
import org.mrdarkimc.itemworth.WorthProvider.Priceable;

import java.util.*;

public final class ItemWorth extends JavaPlugin implements TabCompleter {
    private static ItemWorth instance;

    public static ItemWorth getInstance() {
        return instance;
    }
    public static Configs defaultConfig;

    @Override
    public void onEnable() {
        long l = System.currentTimeMillis();
        //getServer().getPluginManager().registerEvents(new ItemChangeAmmountListener(), this);
        SatanicLib.setupLib(this);
        instance = this;
        defaultConfig = Configs.Defaults.setupConfig();

        getServer().getPluginCommand("worth").setExecutor(this);
        getServer().getPluginCommand("setprice").setExecutor(new SetPriceCmd());
        Set<String> sellableItems = defaultConfig.get().getConfigurationSection("prices").getKeys(false);
        Map<String,Priceable> items = new HashMap<>();
        for (String sellableItem : sellableItems) {
            int buyPrice = defaultConfig.get().getInt("prices." + sellableItem + ".buy");
            int sellPrice = defaultConfig.get().getInt("prices." + sellableItem + ".sell");
            items.put(sellableItem,new Priceable(sellableItem,buyPrice,sellPrice));
        }
        PriceList.setProvider(items);
        long l1 = System.currentTimeMillis();
        System.out.println("Времени затрачено на запуск плагина: " + (l1-l) + " мс");

    }
    PriceChecker priceChecker = new PriceChecker();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = ((Player) sender);
        int price = 0;
        switch (args.length) {
            case 0:
                PriceChecker.Info info = priceChecker.checkSellPriceInfo(((Player) sender).getInventory().getItemInMainHand());
                player.sendMessage(info.message);
                return true;
            case 1:
                for (ItemStack content : Arrays.stream(((Player) sender).getInventory().getContents()).filter(Objects::nonNull).toList()) {
                    price = price + priceChecker.checkSellPrice(content);
                }
                new Message(player,"  %design_clr_primary%Цена всего %design_clr_main%инвентаря &f%img_money%%design_clr_money%" + formatPrice(price),null).send();
        }
        return true;
    }
    public String formatPrice(int price) {
        String priceStr = String.valueOf(price);
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = priceStr.length() - 1; i >= 0; i--) {
            result.append(priceStr.charAt(i));
            count++;

            if (count % 3 == 0 && i > 0) {
                result.append(",");
            }
        }

        return result.reverse().toString();
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.singletonList("inventory");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
