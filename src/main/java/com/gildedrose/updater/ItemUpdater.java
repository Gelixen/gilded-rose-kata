package com.gildedrose.updater;

import com.gildedrose.Item;

public interface ItemUpdater {

    int QUALITY_UPPER_LIMIT = 50;

    void update(Item item);

    static boolean qualityBellowUpperLimit(Item item) {
        return item.quality < QUALITY_UPPER_LIMIT;
    }

    static boolean qualityAboveZero(Item item) {
        return item.quality > 0;
    }

    static boolean sellInBellowZero(Item item) {
        return item.sellIn < 0;
    }
}
