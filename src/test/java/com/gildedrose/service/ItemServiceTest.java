package com.gildedrose.service;

import com.gildedrose.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@RunWith(SpringRunner.class)
public class ItemServiceTest {

    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        itemService = new ItemService(itemRepository);
    }

    @Test
    public void findAll_callRepoOnce() {
        itemService.findAll();

        verify(itemRepository).findAll();
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void updateItems_callRepoThreeTimes() {
        itemService.updateItems();

        verify(itemRepository).findAll();
        verify(itemRepository).deleteAll();
        verify(itemRepository).insert(anyList());
        verifyNoMoreInteractions(itemRepository);
    }
}