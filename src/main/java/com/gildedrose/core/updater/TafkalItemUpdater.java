package com.gildedrose.core.updater;

import com.gildedrose.core.Item;

import static com.gildedrose.core.updater.ItemUpdater.sellInBellowZero;

public class TafkalItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        int rawQuality = item.quality;

        rawQuality++;

        if (item.sellIn < 11) {
            if (item.sellIn < 6) {
                rawQuality += 2;
            } else {
                rawQuality++;
            }
        }

        item.sellIn--;

        if (sellInBellowZero(item)) {
            item.quality = 0;
        } else {
            item.quality = rawQuality > QUALITY_UPPER_LIMIT ? QUALITY_UPPER_LIMIT : rawQuality;
        }
    }

}
