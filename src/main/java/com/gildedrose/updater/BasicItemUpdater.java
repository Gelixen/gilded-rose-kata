package com.gildedrose.updater;

import com.gildedrose.Item;

import static com.gildedrose.updater.ItemUpdater.qualityAboveZero;
import static com.gildedrose.updater.ItemUpdater.sellInBellowZero;

public class BasicItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (qualityAboveZero(item)) {
            item.quality--;
        }

        item.sellIn--;

        if (sellInBellowZero(item) && qualityAboveZero(item)) {
            item.quality--;
        }
    }
}
