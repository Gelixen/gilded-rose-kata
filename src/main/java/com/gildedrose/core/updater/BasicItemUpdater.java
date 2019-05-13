package com.gildedrose.core.updater;

import com.gildedrose.core.Item;

import static com.gildedrose.core.updater.ItemUpdater.sellInBellowZero;

public class BasicItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        int rawQuality = item.quality;

        item.sellIn--;

        if (sellInBellowZero(item)) {
            rawQuality -= 2;
        } else {
            rawQuality--;
        }

        item.quality = rawQuality < 0 ? 0 : rawQuality;
    }

}
