package com.gildedrose;

import java.util.Arrays;

class GildedRose {

    static final String AGED_BRIE = "Aged Brie";
    static final String TAFKAL = "Backstage passes to a TAFKAL80ETC concert";
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int QUALITY_LIMIT = 50;

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = makeDeepCopy(items);
    }

    private Item[] makeDeepCopy(Item[] items) {
        return Arrays.stream(items)
                .map(item -> new Item(item.name, item.sellIn, item.quality))
                .toArray(Item[]::new);
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE) && !item.name.equals(TAFKAL)) {
                if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                    item.quality--;
                }
            } else {
                if (qualityBellowLimit(item)) {
                    item.quality++;

                    if (item.name.equals(TAFKAL)) {
                        if (item.sellIn < 11 && qualityBellowLimit(item)) {
                            item.quality++;
                        }

                        if (item.sellIn < 6 && qualityBellowLimit(item)) {
                            item.quality++;
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(TAFKAL)) {
                        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                            item.quality--;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (qualityBellowLimit(item)) {
                        item.quality++;
                    }
                }
            }
        }
    }

    private boolean qualityBellowLimit(Item item) {
        return item.quality < QUALITY_LIMIT;
    }

    public Item[] getItems() {
        return items;
    }
}