package com.gildedrose;

import com.gildedrose.updater.*;

public enum ItemType {

    AGED_BRIE("Aged Brie") {
        @Override
        ItemUpdater resolveUpdater() {
            return new AgedBrieUpdater();
        }
    },

    TAFKAL("Backstage passes to a TAFKAL80ETC concert") {
        @Override
        ItemUpdater resolveUpdater() {
            return new TafkalItemUpdater();
        }
    },

    SULFURAS("Sulfuras, Hand of Ragnaros") {
        @Override
        ItemUpdater resolveUpdater() {
            return new SulfurasItemUpdater();
        }
    },

    CONJURED("Conjured") {
        @Override
        ItemUpdater resolveUpdater() {
            return new ConjuredItemUpdater();
        }
    },

    BASIC("Basic") {
        @Override
        ItemUpdater resolveUpdater() {
            return new BasicItemUpdater();
        }
    };

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ItemType fromName(String name) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.getName().equalsIgnoreCase(name)) {
                return itemType;
            }
        }

        return name.startsWith(CONJURED.getName()) ? CONJURED : BASIC ;
    }

    abstract ItemUpdater resolveUpdater();
}
