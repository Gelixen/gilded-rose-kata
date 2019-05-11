package com.gildedrose;

import java.util.Arrays;

class GildedRose {
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
                    item.quality = item.quality - 1;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11 && item.quality < 50) {
                            item.quality = item.quality + 1;
                        }

                        if (item.sellIn < 6 && item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    public Item[] getItems() {
        return items;
    }
}