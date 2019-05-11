package com.gildedrose;

import java.util.Arrays;

class GildedRose {

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
            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.quality--;
                }
            } else {
                if (qualityBellowLimit(item)) {
                    item.quality++;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11 && qualityBellowLimit(item)) {
                            item.quality++;
                        }

                        if (item.sellIn < 6 && qualityBellowLimit(item)) {
                            item.quality++;
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
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