package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ItemUpdateScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ItemUpdateScheduler.class);

    private final ItemService itemService;

    public ItemUpdateScheduler(ItemService itemService) {
        this.itemService = itemService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateItems() {
        logger.info("Starting scheduled items update");
        itemService.updateItems();
        logger.info("Ending scheduled items update");
    }
}
