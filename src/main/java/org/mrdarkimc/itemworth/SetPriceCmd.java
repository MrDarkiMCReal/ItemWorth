package org.mrdarkimc.itemworth;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.mrdarkimc.SatanicLib.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SetPriceCmd implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("satanic.price")) return true;
        if (strings.length < 2) {
            soutinfo(commandSender);
            return true;
        }
        if (commandSender instanceof Player player) {
            int sellprice = -1;
            int buyprice = -1;
            try {
                sellprice = Integer.parseInt(strings[0]);
                buyprice = Integer.parseInt(strings[1]);

            } catch (NumberFormatException e) {
                soutinfo(commandSender);
                commandSender.sendMessage("Ошибка в цифре. не такой цифры. Перепроверь");
            }
            if (sellprice == -1) {
                commandSender.sendMessage("Ошибка в цене. не удалось распознать цену для продажи");
                return true;
            }
            if (buyprice == -1) {
                commandSender.sendMessage("Ошибка в цене. не удалось распознать цену для покупки");
                return true;
            }


            Block targetBlock = player.getTargetBlockExact(3);
            if (targetBlock != null) {
                if (targetBlock.getType().equals(Material.CHEST)) {
                    if (targetBlock == null || targetBlock.getType() != Material.CHEST) {
                        player.sendMessage("§cНужно смотреть на сундук!");
                        return false;
                    }

                    Chest chest = (Chest) targetBlock.getState();
                    List<ItemStack> items = Arrays.stream(chest.getInventory().getContents())
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());

                    if (items.isEmpty()) {
                        player.sendMessage("§cСундук пуст!");
                        return false;
                    }

                    for (ItemStack stack : items) {
                        ItemWorth.getInstance().updatePrice(stack, sellprice, buyprice);
                    }
                    ItemWorth.defaultConfig.saveConfig();

                    player.sendMessage(String.format(
                            "§aСохранено %d предметов из сундука. Цены: покупка - %d, продажа - %d",
                            items.size(), buyprice, sellprice
                    ));
                    return true;
                }
            }


            PlayerInventory inventory = player.getInventory();
            ItemStack itemInMainHand = inventory.getItemInMainHand();
            if (itemInMainHand != null && !itemInMainHand.getType().equals(Material.AIR)) {

                ItemWorth.getInstance().updatePrice(itemInMainHand, sellprice, buyprice);
                ItemWorth.defaultConfig.saveConfig();
                player.sendMessage("Сохранен предмет: %s с ценой %s на покупку, и %s на продажу".formatted(itemInMainHand.getType().toString(), buyprice, sellprice));
                return true;
            } else {
                commandSender.sendMessage("Ваша рука пуста, и вы не смотрите на сундук в радиусе 2х блоков");
                return true;
            }
        }
        commandSender.sendMessage("не предвиденная ошибка");
        return false;
    }

    void soutinfo(CommandSender sender) {
        sender.sendMessage(Utils.translateHex("&cНе правильно. &7Правильное использование команды:"));
        sender.sendMessage(Utils.translateHex("    &a/setprice 200 500"));
        sender.sendMessage(Utils.translateHex("    &a/setprice продать купить"));
        sender.sendMessage(Utils.translateHex("    &a500 -&7 цена, за сколько игрок купит предмет в /shop"));
        sender.sendMessage(Utils.translateHex("    &a200 -&7 цена, за сколько игрок продаст /seller "));
        sender.sendMessage(Utils.translateHex(" &7Цену указывать за 1 шт предмета."));
        sender.sendMessage(Utils.translateHex(" &7Команда не считает зачары, количество и т.д"));
        sender.sendMessage(Utils.translateHex(" &7Только сам предмет и все."));
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            return List.of("[цена на покупку в шопе]");
        }
        if (strings.length == 1) {
            return List.of("[цена на продажу скупщику]");
        }
        return null;
    }
}
