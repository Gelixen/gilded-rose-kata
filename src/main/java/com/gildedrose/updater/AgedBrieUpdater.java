package com.gildedrose.updater;

import com.gildedrose.Item;

import static com.gildedrose.updater.ItemUpdater.sellInBellowZero;

public class AgedBrieUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        int rawQuality = item.quality;

        item.sellIn--;

        if (sellInBellowZero(item)) {
            rawQuality += 2;
        } else {
            rawQuality++;
        }

        item.quality = rawQuality > 50 ? 50 : rawQuality;
    }

}
