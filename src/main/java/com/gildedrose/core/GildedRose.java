package com.gildedrose.core;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String TAFKAL = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = makeDeepCopy(items);
    }

    private Item[] makeDeepCopy(Item[] items) {
        return Arrays.stream(items)
                .map(item -> new Item(item.name, item.sellIn, item.quality))
                .toArray(Item[]::new);
    }

    public void updateQualityAsync() {
        CompletableFuture.runAsync(this::updateQuality);
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemType.fromName(item.name).resolveUpdater().update(item);
        }
    }

    public Item[] getItems() {
        return items;
    }
}