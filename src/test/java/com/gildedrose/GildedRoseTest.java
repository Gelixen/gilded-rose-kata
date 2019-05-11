package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Random;

public class GildedRoseTest {

    @Test
    public void zeroSellInAndQualityItem_updateOnce_minusOneSellInZeroQuality() {
        Item[] items = createItems("foo");

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item[] updatedItems = app.getItems();
        assertEquals(1, updatedItems.length);

        assertItem(items[0], updatedItems[0]);
    }

    private Item[] createItems(String itemName) {
        return new Item[] { createItem(itemName) };
    }

    private Item createItem(String itemName) {
        Random random = new Random();
        return new Item(itemName, random.nextInt(10), random.nextInt(10));
    }

    private void assertItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

}
