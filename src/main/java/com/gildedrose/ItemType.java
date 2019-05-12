package com.gildedrose;

enum ItemType {

    AGED_BRIE("Aged Brie") {
        @Override
        void updateQuality(Item item) {
            if (item.quality < 50) {
                item.quality++;
            }

            item.sellIn--;

            if (item.sellIn < 0 && item.quality < 50) {
                item.quality++;
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

    CONJURED("Conjured") {
        @Override
        void updateQuality(Item item) {
            int rawQuality = item.quality;

            rawQuality -= 2;

            item.sellIn--;

            if (item.sellIn < 0) {
                rawQuality -= 2;
            }

            item.quality = rawQuality > 0 ? rawQuality : 0;
        }
    },

    BASIC("Basic") {
        @Override
        void updateQuality(Item item) {
            if (item.quality > 0) {
                item.quality--;
            }

            item.sellIn--;

            if (item.sellIn < 0 && item.quality > 0) {
                item.quality--;
            }
        }
    };

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    public static ItemType fromName(String name) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.name.equalsIgnoreCase(name)) {
                return itemType;
            }
        }

        if (name.startsWith(CONJURED.name)) {
            return CONJURED;
        } else {
            return BASIC;
        }
    }

    abstract void updateQuality(Item item);
}
