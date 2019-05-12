package com.gildedrose;

import java.util.Arrays;

class GildedRose {

    static final String AGED_BRIE = "Aged Brie";
    static final String TAFKAL = "Backstage passes to a TAFKAL80ETC concert";
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private Item[] items;

    GildedRose(Item[] items) {
        this.items = makeDeepCopy(items);
    }

    private Item[] makeDeepCopy(Item[] items) {
        return Arrays.stream(items)
                .map(item -> new Item(item.name, item.sellIn, item.quality))
                .toArray(Item[]::new);
    }

    void updateQuality() {
        new Thread(() -> {
            for (Item item : items) {
                ItemType.fromName(item.name).updateQuality(item);
            }
        }).start();
    }

    Item[] getItems() {
        return items;
    }
}