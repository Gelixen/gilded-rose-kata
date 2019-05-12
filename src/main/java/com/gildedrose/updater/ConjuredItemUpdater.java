package com.gildedrose.updater;

import com.gildedrose.Item;

public class ConjuredItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        int rawQuality = item.quality;

        rawQuality -= 2;

        item.sellIn--;

        if (item.sellIn < 0) {
            rawQuality -= 2;
        }

        item.quality = rawQuality > 0 ? rawQuality : 0;
    }
}
