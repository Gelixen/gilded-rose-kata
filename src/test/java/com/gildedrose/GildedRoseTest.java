package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class GildedRoseTest {

    private Item startingItem;
    private Item expectedItem;

    public GildedRoseTest(Item startingItem, Item expectedItem) {
        this.startingItem = startingItem;
        this.expectedItem = expectedItem;
    }

    @Parameterized.Parameters(name = "{0} -> {1}")
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { new Item("foo", 1, 0), new Item("foo", 0, 0) },
                { new Item("foo", 1, 15), new Item("foo", 0, 14) },
                { new Item("foo", 1, 50), new Item("foo", 0, 49) },
                { new Item("foo", -5, 5), new Item("foo", -6, 3) },
                { new Item("foo", 0, 5), new Item("foo", -1, 3) },
                { new Item("foo", 3, 5), new Item("foo", 2, 4) },
                { new Item("foo", 6, 5), new Item("foo", 5, 4) },
                { new Item("foo", 8, 5), new Item("foo", 7, 4) },
                { new Item("foo", 11, 5), new Item("foo", 10, 4) },
                { new Item("foo", 15, 5), new Item("foo", 14, 4) }
        });
    }

    @Test
    public void testUpdate() {
        GildedRose app = new GildedRose(new Item[]{ startingItem });

        app.updateQuality();

        Item actualItem = app.getItems()[0];
        assertItem(expectedItem, actualItem);
    }

    private void assertItem(Item expected, Item actual) {
        assertEquals("name field mismatch", expected.name, actual.name);
        assertEquals("sellIn field mismatch", expected.sellIn, actual.sellIn);
        assertEquals("quality field mismatch", expected.quality, actual.quality);
    }

}
