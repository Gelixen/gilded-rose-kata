package com.gildedrose.core.updater;

import com.gildedrose.core.Item;

import static com.gildedrose.core.updater.ItemUpdater.sellInBellowZero;

public class ConjuredItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        int rawQuality = item.quality;

        item.sellIn--;

        if (sellInBellowZero(item)) {
            rawQuality -= 4;
        } else {
            rawQuality -= 2;
        }

        item.quality = rawQuality < 0 ? 0 : rawQuality;
    }

}
