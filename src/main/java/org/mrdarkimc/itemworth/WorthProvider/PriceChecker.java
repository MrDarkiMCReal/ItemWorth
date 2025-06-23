package org.mrdarkimc.itemworth.WorthProvider;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;

import java.util.Map;

public class PriceChecker {
    //todo должен возвращать обьект Price что бы детально получить инфу о стоимости предмета(цена/прочность * колво, + зачары и т.д)
    public static int checkSellPrice(ItemStack stack) {
        String material = stack.getType().toString().toLowerCase();
        System.out.println("materialPrice: " + PriceList.getPriceFor(material).getSellprice() * stack.getAmount());
        System.out.println(" ");
        float finalPrice = PriceList.getPriceFor(material).getSellprice() * stack.getAmount();
        System.out.println("Цена материала: " + finalPrice);
        if (!stack.hasItemMeta()) return (int) finalPrice;
        ItemMeta meta = stack.getItemMeta();
        if (meta instanceof Damageable damageableMeta) {
            short maxDurability = stack.getType().getMaxDurability();
            int damage = maxDurability - damageableMeta.getDamage();
            System.out.println("dmg: " + damage);
            System.out.println("maxDura: " + maxDurability);

            if (maxDurability > 0) {
                float percent = (float) damage /maxDurability;
                System.out.println("percent: " + percent);
                finalPrice = finalPrice*percent;

                System.out.println("Цена с учетом износа: " + finalPrice);
            }
        }
        for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
            String key = entry.getKey().getKey().getKey().toLowerCase();
            int level = entry.getValue();
            int buyprice = PriceList.getPriceFor(key).getSellprice();
            int price = (buyprice * level);
            System.out.println("enchant: " + key);
            System.out.println("enchantment price: " + price);
            finalPrice = finalPrice + price;
        }
        System.out.println(" ");
        if (meta instanceof ArmorMeta armorMeta) {
            ArmorTrim trim = armorMeta.getTrim();
            if (trim != null) {
                String trim_material_key = trim.getMaterial().getKey().getKey().toLowerCase();
                String armorTrimPatternNaming = getArmorTrimPatternNaming(armorMeta);
                Priceable patternPrice = PriceList.getPriceFor(armorTrimPatternNaming);
                Priceable material_price = PriceList.getPriceFor(trim_material_key);
                finalPrice = finalPrice + patternPrice.getSellprice() + material_price.getSellprice();
                System.out.println("pattern price: " + patternPrice.getSellprice());
                System.out.println("material price: " + material_price.getSellprice());
            }
        }
        
        System.out.println("Final price: " + finalPrice);
        return (int) finalPrice;
    }

    //    public String getArmorTrimMaterialNaming(ArmorMeta meta){
//        return meta.getTrim().getMaterial().getKey().getKey().toLowerCase() + "_armor_trim_smithing_template";
//    }
    public static String getArmorTrimPatternNaming(ArmorMeta meta) {
        return meta.getTrim().getPattern().getKey().getKey().toLowerCase() + "_armor_trim_smithing_template";
    }
}
