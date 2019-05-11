package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void zeroSellInAndQualityItem_updateOnce_minusOneSellInZeroQuality() {
        Item[] items = createItems(1);

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item[] updatedItems = app.getItems();
        assertEquals(1, updatedItems.length);

        Item firstUpdatedItem = updatedItems[0];
        assertEquals("foo", firstUpdatedItem.name);
        assertEquals(-1, firstUpdatedItem.sellIn);
        assertEquals(0, firstUpdatedItem.quality);
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
