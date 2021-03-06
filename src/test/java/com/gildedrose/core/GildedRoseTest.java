package com.gildedrose.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GildedRoseTest {

    private static final String RANDOM_BASIC_ITEM = "foo";
    private static final String AGED_BRIE = ItemType.AGED_BRIE.getName();
    private static final String TAFKAL = ItemType.TAFKAL.getName();
    private static final String SULFURAS = ItemType.SULFURAS.getName();
    private static final String CONJURED_RANDOM_ITEM = ItemType.CONJURED.getName() + " " + RANDOM_BASIC_ITEM;

    private Item startingItem;
    private Item expectedItem;

    public GildedRoseTest(Item startingItem, Item expectedItem) {
        this.startingItem = startingItem;
        this.expectedItem = expectedItem;
    }

    @Parameterized.Parameters(name = "{0} -> {1}")
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { new Item(RANDOM_BASIC_ITEM, 1, 0), new Item(RANDOM_BASIC_ITEM, 0, 0) },
                { new Item(RANDOM_BASIC_ITEM, 1, 15), new Item(RANDOM_BASIC_ITEM, 0, 14) },
                { new Item(RANDOM_BASIC_ITEM, 1, 50), new Item(RANDOM_BASIC_ITEM, 0, 49) },

                { new Item(RANDOM_BASIC_ITEM, -5, 5), new Item(RANDOM_BASIC_ITEM, -6, 3) }, // S < 0    Q - 2
                { new Item(RANDOM_BASIC_ITEM, 0, 5), new Item(RANDOM_BASIC_ITEM, -1, 3) }, // S < 0    Q - 2
                { new Item(RANDOM_BASIC_ITEM, 3, 5), new Item(RANDOM_BASIC_ITEM, 2, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_BASIC_ITEM, 6, 5), new Item(RANDOM_BASIC_ITEM, 5, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_BASIC_ITEM, 8, 5), new Item(RANDOM_BASIC_ITEM, 7, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_BASIC_ITEM, 11, 5), new Item(RANDOM_BASIC_ITEM, 10, 4) }, // S> 0    Q - 1
                { new Item(RANDOM_BASIC_ITEM, 15, 5), new Item(RANDOM_BASIC_ITEM, 14, 4) }, // S> 0    Q - 1

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

                { new Item(CONJURED_RANDOM_ITEM, 4, 15), new Item(CONJURED_RANDOM_ITEM, 3, 13) }, // S > 0    Q - 2
                { new Item(CONJURED_RANDOM_ITEM, 4, 1), new Item(CONJURED_RANDOM_ITEM, 3, 0) }, // Q = 1 & S > 0    Q - 1
                { new Item(CONJURED_RANDOM_ITEM, 0, 15), new Item(CONJURED_RANDOM_ITEM, -1, 11) }, // S <= 0    Q - 4
                { new Item(CONJURED_RANDOM_ITEM, 0, 3), new Item(CONJURED_RANDOM_ITEM, -1, 0) }, // Q = 3 & S <= 0    Q - 1
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
