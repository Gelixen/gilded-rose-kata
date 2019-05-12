package com.gildedrose.updater;

import com.gildedrose.Item;

import static com.gildedrose.updater.ItemUpdater.qualityBellowUpperLimit;
import static com.gildedrose.updater.ItemUpdater.sellInBellowZero;

public class AgedBrieUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (qualityBellowUpperLimit(item)) {
            item.quality++;
        }

        item.sellIn--;

        if (sellInBellowZero(item) && qualityBellowUpperLimit(item)) {
            item.quality++;
        }
    }

}