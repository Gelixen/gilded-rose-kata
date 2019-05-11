package com.gildedrose;

enum ItemType {

    AGED_BRIE("Aged Brie") {
        @Override
        void updateQuality(Item item) {
            if (item.quality < 50) {
                item.quality++;
            }

            item.sellIn--;

            if (item.sellIn < 0) {
                if (item.quality < 50) {
                    item.quality++;
                }
            }
        }
    },

    TAFKAL("Backstage passes to a TAFKAL80ETC concert") {
        @Override
        void updateQuality(Item item) {
            if (item.quality < 50) {
                item.quality++;

                if (item.sellIn < 11 && item.quality < 50) {
                    item.quality++;
                }

                if (item.sellIn < 6 && item.quality < 50) {
                    item.quality++;
                }
            }

            item.sellIn--;

            if (item.sellIn < 0) {
                item.quality = 0;
            }
        }
    },

    SULFURAS("Sulfuras, Hand of Ragnaros") {
        @Override
        void updateQuality(Item item) {}
    },

    BASIC("Basic") {
        @Override
        void updateQuality(Item item) {
            if (item.quality > 0) {
                item.quality--;
            }

            item.sellIn--;

            if (item.sellIn < 0) {
                if (item.quality > 0) {
                    item.quality--;
                }
            }
        }
    };

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    public static ItemType fromName(String name) {
        for (ItemType item : ItemType.values()) {
            if (item.name.equalsIgnoreCase(name)) {
                return item;
            }
        }
        return BASIC;
    }

    abstract void updateQuality(Item item);
}
