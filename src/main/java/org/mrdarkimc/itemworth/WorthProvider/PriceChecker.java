package org.mrdarkimc.itemworth.WorthProvider;

import org.bukkit.Bukkit;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;

import java.util.Map;

public class PriceChecker {
    public static class Info {
        public float price;
        public String message;

        public Info(float price, String message) {
            this.price = price;
            this.message = message;
        }
    }

    public static int getPriceForBuyingItem(ItemStack stack) {
        Info info = getBuyPriceInfo(stack);
        return (int) info.price;
    }

    public static int getPriceForSellingItem(ItemStack stack) {
        Info info = getSellPriceInfo(stack);
        return (int) info.price;
    }

    public static Info getBuyPriceInfo(ItemStack stack) {
        return calculatePriceInfo(stack, PriceType.BUY);
    }

    public static Info getSellPriceInfo(ItemStack stack) {
        return calculatePriceInfo(stack, PriceType.SELL);
    }

    private static Info calculatePriceInfo(ItemStack stack, PriceType priceType) {
        String material = stack.getType().toString().toUpperCase();
        int basePrice = getBasePrice(material, priceType);
        float finalPrice = basePrice * stack.getAmount();

        StringBuilder builder = new StringBuilder();
        appendBaseInfo(builder, material, finalPrice, basePrice, priceType);

        if (stack.hasItemMeta()) {
            ItemMeta meta = stack.getItemMeta();

            finalPrice = applyDurabilityModifier(stack, meta, finalPrice, builder);
            finalPrice = applyEnchantments(meta, finalPrice, builder, priceType);
            finalPrice = applyArmorTrim(meta, finalPrice, builder, priceType);
            finalPrice = applyShulkerContents(meta, finalPrice, builder, priceType);
        }

        appendFinalPrice(builder, finalPrice);
        logInfo(builder.toString());

        return new Info(finalPrice, builder.toString());
    }
    private static float applyShulkerContents(ItemMeta meta, float currentPrice, StringBuilder builder, PriceType priceType) {
        if (meta instanceof BlockStateMeta bsm) {
            if (bsm.getBlockState() instanceof ShulkerBox shulker) {
                float contentsPrice = 0;

                for (ItemStack contentItem : shulker.getInventory().getContents()) {
                    if (contentItem != null && !contentItem.getType().isAir()) {
                        Info info = (priceType == PriceType.BUY)
                                ? getBuyPriceInfo(contentItem)
                                : getSellPriceInfo(contentItem);

                        contentsPrice += info.price;
                    }
                }

                if (contentsPrice > 0) {
                    currentPrice += contentsPrice;
                    builder.append("\n  + Содержимое шалкера: ").append(contentsPrice);
                }
            }
        }
        return currentPrice;
    }

    private static int getBasePrice(String material, PriceType priceType) {
        Priceable priceable = PriceList.getPriceFor(material);
        return priceType == PriceType.BUY ? priceable.getBuyprice() : priceable.getSellprice();
    }

    private static void appendBaseInfo(StringBuilder builder, String material,
                                       float finalPrice, int basePrice, PriceType priceType) {
        String action = priceType == PriceType.BUY ? "покупке" : "продаже";
        builder.append("Информация о ").append(action).append(" предмета: ").append("\n")
                .append("Материал: ").append(material).append("\n")
                .append("Цена материала с учетом кол-ва: ").append(finalPrice)
                .append(" (").append(basePrice).append(") за штуку").append("\n");
    }

    private static float applyDurabilityModifier(ItemStack stack, ItemMeta meta,
                                                 float currentPrice, StringBuilder builder) {
        if (meta instanceof Damageable damageableMeta) {
            short maxDurability = stack.getType().getMaxDurability();
            int remainingDurability = maxDurability - damageableMeta.getDamage();

            if (maxDurability > 0) {
                float durabilityPercent = (float) remainingDurability / maxDurability;
                float newPrice = currentPrice * durabilityPercent;

                builder.append("Износ предмета: ").append(remainingDurability)
                        .append("/").append(maxDurability)
                        .append(" (").append(String.format("%.2f", durabilityPercent)).append(")").append("\n")
                        .append("Цена с учетом износа: ").append(newPrice).append("\n");

                return newPrice;
            }
        }
        return currentPrice;
    }

    private static float applyEnchantments(ItemMeta meta, float currentPrice,
                                           StringBuilder builder, PriceType priceType) {
        float finalPrice = currentPrice;

        for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
            String enchantmentKey = entry.getKey().getKey().getKey().toUpperCase();
            int level = entry.getValue();
            int enchantmentPrice = getEnchantmentPrice(enchantmentKey, priceType);
            int enchantmentTotal = enchantmentPrice * level;

            builder.append("Зачарование: ").append(enchantmentKey)
                    .append(" Цена: ").append(enchantmentTotal)
                    .append(" (").append(enchantmentPrice).append(" за ур.)").append("\n");

            finalPrice += enchantmentTotal;
        }

        return finalPrice;
    }

    private static int getEnchantmentPrice(String enchantmentKey, PriceType priceType) {
        Priceable priceable = PriceList.getPriceFor(enchantmentKey);
        return priceType == PriceType.BUY ? priceable.getBuyprice() : priceable.getSellprice();
    }

    private static float applyArmorTrim(ItemMeta meta, float currentPrice,
                                        StringBuilder builder, PriceType priceType) {
        if (meta instanceof ArmorMeta armorMeta) {
            ArmorTrim trim = armorMeta.getTrim();
            if (trim != null) {
                String trimMaterialKey = trim.getMaterial().getKey().getKey().toLowerCase();
                String trimPatternName = getArmorTrimPatternNaming(armorMeta);

                Priceable patternPriceable = PriceList.getPriceFor(trimPatternName);
                Priceable materialPriceable = PriceList.getPriceFor(trimMaterialKey);

                int patternPrice = priceType == PriceType.BUY ?
                        patternPriceable.getBuyprice() : patternPriceable.getSellprice();
                int materialPrice = priceType == PriceType.BUY ?
                        materialPriceable.getBuyprice() : materialPriceable.getSellprice();

                float trimTotal = patternPrice + materialPrice;

                builder.append("АрморТрим Материал: ").append(trimMaterialKey)
                        .append("(").append(materialPrice).append(") ").append("\n")
                        .append("АрморТрим Паттерн: ").append(trimPatternName)
                        .append("(").append(patternPrice).append(") ").append("\n");

                return currentPrice + trimTotal;
            }
        }
        return currentPrice;
    }

    private static void appendFinalPrice(StringBuilder builder, float finalPrice) {
        builder.append("Финальная цена: ").append(finalPrice);
    }

    private static void logInfo(String message) {
        Bukkit.getLogger().info(message);
    }

    public static String getArmorTrimPatternNaming(ArmorMeta meta) {
        return meta.getTrim().getPattern().getKey().getKey().toLowerCase() + "_armor_trim_smithing_template";
    }
    private enum PriceType {
        BUY, SELL
    }
}