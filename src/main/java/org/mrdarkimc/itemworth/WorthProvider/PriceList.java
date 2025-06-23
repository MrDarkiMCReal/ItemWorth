package org.mrdarkimc.itemworth.WorthProvider;

import java.util.HashMap;
import java.util.Map;

public class PriceList {
    private static final Priceable empty = new Priceable("empty",0,0);
    private static Map<String,Priceable> map = new HashMap<>();
    static {
        map.put("diamond",new Priceable(50,20));
        map.put("netherite",new Priceable(50,20));
        map.put("gold_ingot",new Priceable(50,20));
        map.put("iron_ingot",new Priceable(50,20));
        map.put("copper_ingot",new Priceable(50,20));
        map.put("amethyst_shard",new Priceable(50,20));
        map.put("lapis_lazuli",new Priceable(50,20));
        map.put("emerald",new Priceable(50,20));
        map.put("elytra",new Priceable(50,20000));

        map.put("diamond_helmet",new Priceable(500,100));
        map.put("diamond_chestplate",new Priceable(500,100));
        map.put("diamond_leggings",new Priceable(500,100));
        map.put("diamond_boots",new Priceable(500,100));
        map.put("netherite_helmet",new Priceable(500,100));
        map.put("netherite_chestplate",new Priceable(500,100));
        map.put("netherite_leggings",new Priceable(500,100));
        map.put("netherite_boots",new Priceable(500,100));
        map.put("snout_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("BOLT_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("COAST_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("DUNE_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("EYE_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("FLOW_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("HOST_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("RAISER_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("RIB_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("SENTRY_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("SHAPER_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("SILENCE_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("SPIRE_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("TIDE_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("VEX_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("WARD_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("WAYFINDER_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));
        map.put("WILD_armor_trim_smithing_template".toLowerCase(),new Priceable(500,500));

        map.put("BREACH".toLowerCase(),new Priceable(500,100));
        map.put("CHANNELING".toLowerCase(),new Priceable(500,100));
        map.put("DENSITY".toLowerCase(),new Priceable(500,100));
        map.put("DEPTH_STRIDER".toLowerCase(),new Priceable(500,100));
        map.put("EFFICIENCY".toLowerCase().toLowerCase(),new Priceable(500,100));
        map.put("FEATHER_FALLING".toLowerCase(),new Priceable(500,100));
        map.put("FIRE_ASPECT".toLowerCase(),new Priceable(500,100));
        map.put("FIRE_PROTECTION".toLowerCase(),new Priceable(500,100));
        map.put("FLAME".toLowerCase(),new Priceable(500,100));
        map.put("FORTUNE".toLowerCase(),new Priceable(500,100));
        map.put("FROST_WALKER".toLowerCase(),new Priceable(500,100));
        map.put("IMPALING".toLowerCase(),new Priceable(500,100));
        map.put("INFINITY".toLowerCase(),new Priceable(500,100));
        map.put("KNOCKBACK".toLowerCase(),new Priceable(500,100));
        map.put("LOOTING".toLowerCase(),new Priceable(500,100));
        map.put("LOYALTY".toLowerCase(),new Priceable(500,100));
        map.put("LUCK_OF_THE_SEA".toLowerCase(),new Priceable(500,100));
        map.put("LURE".toLowerCase(),new Priceable(500,100));
        map.put("MENDING".toLowerCase(),new Priceable(500,100));
        map.put("MULTISHOT".toLowerCase(),new Priceable(500,100));
        map.put("POWER".toLowerCase(),new Priceable(500,100));
        map.put("PROJECTILE_PROTECTION".toLowerCase(),new Priceable(500,100));
        map.put("PROTECTION".toLowerCase(),new Priceable(500,100));
        map.put("PUNCH".toLowerCase(),new Priceable(500,100));
        map.put("QUICK_CHARGE".toLowerCase(),new Priceable(500,100));
        map.put("RESPIRATION".toLowerCase(),new Priceable(500,100));
        map.put("RIPTIDE".toLowerCase(),new Priceable(500,100));
        map.put("SHARPNESS".toLowerCase(),new Priceable(500,100));
        map.put("SILK_TOUCH".toLowerCase(),new Priceable(500,100));
        map.put("SMITE".toLowerCase(),new Priceable(500,100));
        map.put("SOUL_SPEED".toLowerCase(),new Priceable(500,100));
        map.put("SWEEPING_EDGE".toLowerCase(),new Priceable(500,100));
        map.put("SWIFT_SNEAK".toLowerCase(),new Priceable(500,100));
        map.put("THORNS".toLowerCase(),new Priceable(500,100));
        map.put("UNBREAKING".toLowerCase(),new Priceable(500,100));
        map.put("WIND_BURST".toLowerCase(),new Priceable(500,100));
        map.put("blast_protection".toLowerCase(),new Priceable(500,100));
    }
    public static Priceable getPriceFor(String name){
        return map.getOrDefault(name, empty);
    }
}
