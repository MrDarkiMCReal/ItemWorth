package org.mrdarkimc.itemworth.WorthProvider;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;

import java.util.Map;

public class PriceChecker {
    public static class Info{
       public float price;
       public String message;

        public Info(float price, String message) {
            this.price = price;
            this.message = message;
        }
    }
    public static int checkSellPrice(ItemStack stack) {
        Info info = checkSellPriceInfo(stack);
        return (int) info.price;
//        String material = stack.getType().toString().toUpperCase();
//        int sellprice = PriceList.getPriceFor(material).getSellprice();
//        float finalPrice = sellprice * stack.getAmount();
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("Информация о продаже предмета: ").append("\n")
//                .append("Материал: ").append(material).append("\n")
//                .append("Цена материала с учетом кол-ва: ").append(finalPrice).append(" (").append(sellprice).append(") за штуку").append("\n");
//
//        if (!stack.hasItemMeta()){
//            Bukkit.getLogger().info(builder.toString());
//            return (int) finalPrice;
//        }
//        ItemMeta meta = stack.getItemMeta();
//        if (meta instanceof Damageable damageableMeta) {
//            short maxDurability = stack.getType().getMaxDurability();
//            int damage = maxDurability - damageableMeta.getDamage();
//
//
//            if (maxDurability > 0) {
//                float percent = (float) damage /maxDurability;
//                finalPrice = finalPrice*percent;
//                builder.append("Износ предмета: ").append(damage).append("/").append(maxDurability).append(" (").append(percent).append(")").append("\n")
//                                .append("Цена с учетом износа: ").append(finalPrice).append("\n");
//            }
//
//        }
//        for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
//            String key = entry.getKey().getKey().getKey().toUpperCase();
//            int level = entry.getValue();
//            int buyprice = PriceList.getPriceFor(key).getSellprice();
//            int price = (buyprice * level);
//            builder.append("Зачарование: ").append(key).append(" Цена: ").append(price).append(" (").append(buyprice).append(" за шт)").append("\n");
//            finalPrice = finalPrice + price;
//        }
//        if (meta instanceof ArmorMeta armorMeta) {
//            ArmorTrim trim = armorMeta.getTrim();
//            if (trim != null) {
//                String trim_material_key = trim.getMaterial().getKey().getKey().toLowerCase();
//                String armorTrimPatternNaming = getArmorTrimPatternNaming(armorMeta);
//                Priceable patternPrice = PriceList.getPriceFor(armorTrimPatternNaming);
//                Priceable material_price = PriceList.getPriceFor(trim_material_key);
//                finalPrice = finalPrice + patternPrice.getSellprice() + material_price.getSellprice();
//                builder.append("АрморТрим Материал: ").append(trim_material_key).append("(").append(material_price.getSellprice()).append(") ").append("\n")
//                        .append("АрморТрим Паттерн: ").append(armorTrimPatternNaming).append("(").append(patternPrice.getSellprice()).append(") ").append("\n");
//            }
//        }
//
//
//
//        builder.append("Финальная цена: ").append(finalPrice);
//        Bukkit.getLogger().info(builder.toString());
//        new Info(finalPrice,builder.toString());
//        return (int) finalPrice;
    }
    public static Info checkSellPriceInfo(ItemStack stack) {
        String material = stack.getType().toString().toUpperCase();
        int sellprice = PriceList.getPriceFor(material).getSellprice();
        float finalPrice = sellprice * stack.getAmount();

        StringBuilder builder = new StringBuilder();
        builder.append("Информация о продаже предмета: ").append("\n")
                .append("Материал: ").append(material).append("\n")
                .append("Цена материала с учетом кол-ва: ").append(finalPrice).append(" (").append(sellprice).append(") за штуку").append("\n");

        if (!stack.hasItemMeta()){
            Bukkit.getLogger().info(builder.toString());
            return new Info(finalPrice,builder.toString());
        }
        ItemMeta meta = stack.getItemMeta();
        if (meta instanceof Damageable damageableMeta) {
            short maxDurability = stack.getType().getMaxDurability();
            int damage = maxDurability - damageableMeta.getDamage();


            if (maxDurability > 0) {
                float percent = (float) damage /maxDurability;
                finalPrice = finalPrice*percent;
                builder.append("Износ предмета: ").append(damage).append("/").append(maxDurability).append(" (").append(percent).append(")").append("\n")
                        .append("Цена с учетом износа: ").append(finalPrice).append("\n");
            }

        }
        for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
            String key = entry.getKey().getKey().getKey().toUpperCase();
            int level = entry.getValue();
            int buyprice = PriceList.getPriceFor(key).getSellprice();
            int price = (buyprice * level);
            builder.append("Зачарование: ").append(key).append(" Цена: ").append(price).append(" (").append(buyprice).append(" за шт)").append("\n");
            finalPrice = finalPrice + price;
        }
        if (meta instanceof ArmorMeta armorMeta) {
            ArmorTrim trim = armorMeta.getTrim();
            if (trim != null) {
                String trim_material_key = trim.getMaterial().getKey().getKey().toLowerCase();
                String armorTrimPatternNaming = getArmorTrimPatternNaming(armorMeta);
                Priceable patternPrice = PriceList.getPriceFor(armorTrimPatternNaming);
                Priceable material_price = PriceList.getPriceFor(trim_material_key);
                finalPrice = finalPrice + patternPrice.getSellprice() + material_price.getSellprice();
                builder.append("АрморТрим Материал: ").append(trim_material_key).append("(").append(material_price.getSellprice()).append(") ").append("\n")
                        .append("АрморТрим Паттерн: ").append(armorTrimPatternNaming).append("(").append(patternPrice.getSellprice()).append(") ").append("\n");
            }
        }



        builder.append("Финальная цена: ").append(finalPrice);
        Bukkit.getLogger().info(builder.toString());

        return new Info(finalPrice,builder.toString());
    }

    //    public String getArmorTrimMaterialNaming(ArmorMeta meta){
//        return meta.getTrim().getMaterial().getKey().getKey().toLowerCase() + "_armor_trim_smithing_template";
//    }
    public static String getArmorTrimPatternNaming(ArmorMeta meta) {
        return meta.getTrim().getPattern().getKey().getKey().toLowerCase() + "_armor_trim_smithing_template";
    }
}
