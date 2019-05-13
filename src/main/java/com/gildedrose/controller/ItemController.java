package com.gildedrose.controller;

import com.gildedrose.core.Item;
import com.gildedrose.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/")
    public List<Item> index() {
        return itemService.findAll();
    }
}
