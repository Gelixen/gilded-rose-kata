package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void zeroSellInAndQualityItem_updateOnce_minusOneSellInZeroQuality() {
        Item[] items = createItems(1);

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(1, app.items.length);
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    private Item[] createItems(int count) {
        Item[] items = new Item[count];

        for (int i = 0; i < count; i++) {
            items[i] = createItem();
        }

        return items;
    }

    private Item createItem() {
        return new Item("foo", 0, 0);
    }

}
