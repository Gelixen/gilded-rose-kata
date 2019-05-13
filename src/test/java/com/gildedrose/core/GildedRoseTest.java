package com.gildedrose.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.gildedrose.core.GildedRose.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GildedRoseTest {

    private static final String RANDOM_NAME = "foo";
    private static final String RANDOM_CONJURED_NAME = "Conjured foo";

    private Item startingItem;
    private Item expectedItem;

    public GildedRoseTest(Item startingItem, Item expectedItem) {
        this.startingItem = startingItem;
        this.expectedItem = expectedItem;
    }

    @Parameterized.Parameters(name = "{0} -> {1}")
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { new Item(RANDOM_NAME, 1, 0), new Item(RANDOM_NAME, 0, 0) },
                { new Item(RANDOM_NAME, 1, 15), new Item(RANDOM_NAME, 0, 14) },
                { new Item(RANDOM_NAME, 1, 50), new Item(RANDOM_NAME, 0, 49) },

                { new Item(RANDOM_NAME, -5, 5), new Item(RANDOM_NAME, -6, 3) }, // S < 0    Q - 2
                { new Item(RANDOM_NAME, 0, 5), new Item(RANDOM_NAME, -1, 3) }, // S < 0    Q - 2
                { new Item(RANDOM_NAME, 3, 5), new Item(RANDOM_NAME, 2, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_NAME, 6, 5), new Item(RANDOM_NAME, 5, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_NAME, 8, 5), new Item(RANDOM_NAME, 7, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_NAME, 11, 5), new Item(RANDOM_NAME, 10, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_NAME, 15, 5), new Item(RANDOM_NAME, 14, 4) }, // S> 0    Q - 1

                { new Item(AGED_BRIE, 15, 50), new Item(AGED_BRIE, 14, 50) }, // Q = 50 & S >= 0    Q (no change)
                { new Item(AGED_BRIE, -5, 50), new Item(AGED_BRIE, -6, 50) }, // Q = 50 & S < 0    Q (no change)
                { new Item(AGED_BRIE, 15, 10), new Item(AGED_BRIE, 14, 11) }, // Q < 50 & S >= 0    Q + 1
                { new Item(AGED_BRIE, -5, 49), new Item(AGED_BRIE, -6, 50) }, // Q < 50 & S < 0    Q + 1
                { new Item(AGED_BRIE, -5, 10), new Item(AGED_BRIE, -6, 12) }, // Q < 49 & S < 0    Q + 2

                { new Item(TAFKAL, 15, 50), new Item(TAFKAL, 14, 50) }, // Q = 50    Q (no change)
                { new Item(TAFKAL, 15, 40), new Item(TAFKAL, 14, 41) }, // Q < 50 & S >= 11    Q + 1
                { new Item(TAFKAL, 10, 49), new Item(TAFKAL, 9, 50) }, // Q < 50 & 11 < S <= 6    Q + 1
                { new Item(TAFKAL, 10, 45), new Item(TAFKAL, 9, 47) }, // Q < 49 & 11 < S <= 6    Q + 2
                { new Item(TAFKAL, 5, 48), new Item(TAFKAL, 4, 50) }, // Q < 49 & S < 6    Q + 2
                { new Item(TAFKAL, 5, 46), new Item(TAFKAL, 4, 49) }, // Q < 48 & S < 6    Q + 3
                { new Item(TAFKAL, -2, 46), new Item(TAFKAL, -3, 0) }, // Q < 48 & S < 0    Q = 0

                { new Item(SULFURAS, 15, 60), new Item(SULFURAS, 15, 60) }, // Q >= 50 & S >= 0    Q & S no changes
                { new Item(SULFURAS, 15, 60), new Item(SULFURAS, 15, 60) }, // Q >= 50 & S < 0    Q & S no changes
                { new Item(SULFURAS, 15, 5), new Item(SULFURAS, 15, 5) }, // 0 < Q < 50 & S >= 0    Q & S no changes
                { new Item(SULFURAS, 15, 5), new Item(SULFURAS, 15, 5) }, // 0 < Q < 50 & S < 0    Q & S no changes
                { new Item(SULFURAS, 15, 0), new Item(SULFURAS, 15, 0) }, // Q = 0 & S >= 0    Q & S no changes
                { new Item(SULFURAS, 15, 0), new Item(SULFURAS, 15, 0) }, // Q = 0 & S < 0    Q & S no changes

                { new Item(RANDOM_CONJURED_NAME, 4, 15), new Item(RANDOM_CONJURED_NAME, 3, 13) }, // S > 0    Q - 2
                { new Item(RANDOM_CONJURED_NAME, 4, 1), new Item(RANDOM_CONJURED_NAME, 3, 0) }, // Q = 1 & S > 0    Q - 1
                { new Item(RANDOM_CONJURED_NAME, 0, 15), new Item(RANDOM_CONJURED_NAME, -1, 11) }, // S <= 0    Q - 4
                { new Item(RANDOM_CONJURED_NAME, 0, 3), new Item(RANDOM_CONJURED_NAME, -1, 0) }, // Q = 3 & S <= 0    Q - 1
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
