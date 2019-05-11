package com.gildedrose;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @RequestMapping("/")
    public Item[] index() {
        Item item = new Item("foo", 1, 1);
        Item item2 = new Item("foo2", 2, 2);
        Item item3 = new Item("foo3", 3, 3);
        return new Item[] { item, item2, item3 };
    }
}
