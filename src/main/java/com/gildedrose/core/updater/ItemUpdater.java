package com.gildedrose.core.updater;

import com.gildedrose.core.Item;

public interface ItemUpdater {

    int QUALITY_UPPER_LIMIT = 50;

    void update(Item item);

    static boolean sellInBellowZero(Item item) {
        return item.sellIn < 0;
    }
}
