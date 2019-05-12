package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void updateItems() {
        Item[] items = findItems();

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        updateDB(gildedRose.getItems());
    }

    private Item[] findItems() {
        List<Item> items = itemRepository.findAll();
        logItems(items);
        return items.toArray(new Item[0]);
    }

    private void updateDB(Item[] items) {
        itemRepository.deleteAll();
        itemRepository.insert(Arrays.asList(items));
    }

    private void logItems(List<Item> items) {
        for (Item item: items) {
            logger.info(item.toString());
        }
    }
}
