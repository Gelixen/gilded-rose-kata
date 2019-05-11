package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void zeroSellInAndQualityItem_updateOnce_minusOneSellInZeroQuality() {
        Item[] items = createItems("foo", 0, 0);

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item[] updatedItems = app.getItems();
        assertEquals(1, updatedItems.length);

        assertItem(items[0], updatedItems[0]);
    }

    @Test
    public void twoSellInOneQualityItem_updateOnce_OneSellInZeroQuality() {
        Item[] items = createItems("foo", 2, 1);

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item[] updatedItems = app.getItems();
        assertEquals(1, updatedItems.length);

        assertItem(items[0], updatedItems[0]);
    }

    private Item[] createItems(String itemName, int sellIn, int quality) {
        Item item = new Item(itemName, sellIn, quality);
        return new Item[] { item };
    }

    private void assertItem(Item expected, Item actual) {
        assertEquals("name field mismatch", expected.name, actual.name);
        assertEquals("sellIn field mismatch", expected.sellIn - 1, actual.sellIn);
        assertEquals("quality field mismatch", 0, actual.quality);
    }

}
