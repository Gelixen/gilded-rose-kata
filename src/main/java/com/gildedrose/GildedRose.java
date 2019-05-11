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
            if (item.name.equals(SULFURAS)) {
                continue;
            }

            if (item.name.equals(AGED_BRIE) || item.name.equals(TAFKAL)) {
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
            } else {
                if (item.quality > 0) {
                    item.quality--;
                }
            }

            item.sellIn--;

            if (item.sellIn < 0) {
                if (item.name.equals(AGED_BRIE)) {
                    if (qualityBellowLimit(item)) {
                        item.quality++;
                    }
                } else {
                    if (item.name.equals(TAFKAL)) {
                        item.quality = 0;
                    } else {
                        if (item.quality > 0) {
                            item.quality--;
                        }
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