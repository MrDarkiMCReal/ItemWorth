package org.mrdarkimc.itemworth.WorthProvider;

import java.util.HashMap;
import java.util.Map;

public class PriceList {
    private static final Priceable empty = new Priceable("empty", 0, 0);
    private static Map<String, Priceable> prices = new HashMap<>();

    public static void setProvider(Map<String, Priceable> priceableMap) {
        prices = priceableMap;
    }

    public static Priceable getPriceFor(String name) {
        return prices.getOrDefault(name, empty);
    }

    public static void add(String key, Priceable priceable) {
        prices.put(key, priceable);
    }

    public static Map<String, ? extends Priceable> getPriceables() {
        return prices;
    }

    static {
        prices.put("snout_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("BOLT_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("COAST_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("DUNE_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("EYE_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("FLOW_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("HOST_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("RAISER_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("RIB_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("SENTRY_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("SHAPER_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("SILENCE_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("SPIRE_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("TIDE_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("VEX_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("WARD_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("WAYFINDER_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));
        prices.put("WILD_armor_trim_smithing_template".toLowerCase(), new Priceable(500, 500));

        prices.put("BREACH".toLowerCase(), new Priceable(500, 100));
        prices.put("CHANNELING".toLowerCase(), new Priceable(500, 100));
        prices.put("DENSITY".toLowerCase(), new Priceable(500, 100));
        prices.put("DEPTH_STRIDER".toLowerCase(), new Priceable(500, 100));
        prices.put("EFFICIENCY".toLowerCase().toLowerCase(), new Priceable(500, 100));
        prices.put("FEATHER_FALLING".toLowerCase(), new Priceable(500, 100));
        prices.put("FIRE_ASPECT".toLowerCase(), new Priceable(500, 100));
        prices.put("FIRE_PROTECTION".toLowerCase(), new Priceable(500, 100));
        prices.put("FLAME".toLowerCase(), new Priceable(500, 100));
        prices.put("FORTUNE".toLowerCase(), new Priceable(500, 100));
        prices.put("FROST_WALKER".toLowerCase(), new Priceable(500, 100));
        prices.put("IMPALING".toLowerCase(), new Priceable(500, 100));
        prices.put("INFINITY".toLowerCase(), new Priceable(500, 100));
        prices.put("KNOCKBACK".toLowerCase(), new Priceable(500, 100));
        prices.put("LOOTING".toLowerCase(), new Priceable(500, 100));
        prices.put("LOYALTY".toLowerCase(), new Priceable(500, 100));
        prices.put("LUCK_OF_THE_SEA".toLowerCase(), new Priceable(500, 100));
        prices.put("LURE".toLowerCase(), new Priceable(500, 100));
        prices.put("MENDING".toLowerCase(), new Priceable(500, 100));
        prices.put("MULTISHOT".toLowerCase(), new Priceable(500, 100));
        prices.put("POWER".toLowerCase(), new Priceable(500, 100));
        prices.put("PROJECTILE_PROTECTION".toLowerCase(), new Priceable(500, 100));
        prices.put("PROTECTION".toLowerCase(), new Priceable(500, 100));
        prices.put("PUNCH".toLowerCase(), new Priceable(500, 100));
        prices.put("QUICK_CHARGE".toLowerCase(), new Priceable(500, 100));
        prices.put("RESPIRATION".toLowerCase(), new Priceable(500, 100));
        prices.put("RIPTIDE".toLowerCase(), new Priceable(500, 100));
        prices.put("SHARPNESS".toLowerCase(), new Priceable(500, 100));
        prices.put("SILK_TOUCH".toLowerCase(), new Priceable(500, 100));
        prices.put("SMITE".toLowerCase(), new Priceable(500, 100));
        prices.put("SOUL_SPEED".toLowerCase(), new Priceable(500, 100));
        prices.put("SWEEPING_EDGE".toLowerCase(), new Priceable(500, 100));
        prices.put("SWIFT_SNEAK".toLowerCase(), new Priceable(500, 100));
        prices.put("THORNS".toLowerCase(), new Priceable(500, 100));
        prices.put("UNBREAKING".toLowerCase(), new Priceable(500, 100));
        prices.put("WIND_BURST".toLowerCase(), new Priceable(500, 100));
        prices.put("blast_protection".toLowerCase(), new Priceable(500, 100));
    }
}
