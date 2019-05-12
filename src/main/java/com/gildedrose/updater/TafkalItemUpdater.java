package com.gildedrose.updater;

import com.gildedrose.Item;

import static com.gildedrose.updater.ItemUpdater.qualityBellowUpperLimit;
import static com.gildedrose.updater.ItemUpdater.sellInBellowZero;

public class TafkalItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (qualityBellowUpperLimit(item)) {
            item.quality++;

            if (item.sellIn < 11 && qualityBellowUpperLimit(item)) {
                item.quality++;
            }

            if (item.sellIn < 6 && qualityBellowUpperLimit(item)) {
                item.quality++;
            }
        }

        item.sellIn--;

        if (sellInBellowZero(item)) {
            item.quality = 0;
        }
    }
}
