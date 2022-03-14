package com.example.slsmettle.application;

import com.example.slsmettle.application.dto.ItemDto;
import com.example.slsmettle.domain.ItemPersistenceSpi;
import com.example.slsmettle.domain.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemApplicationService {

    private final ItemPersistenceSpi itemPersistenceSpi;

    public ItemApplicationService(ItemPersistenceSpi itemPersistenceSpi) {
        this.itemPersistenceSpi = itemPersistenceSpi;
    }

    public ItemDto getItem(String id){
        Item item = itemPersistenceSpi.get(id);
        return getItemDto(item);
    }

    public ItemDto createItem(ItemDto itemDto){
        Item item = Item.create(itemDto.getName(), itemDto.getType(), itemDto.getDescription(), itemDto.getCost());
        itemPersistenceSpi.persist(item);
        return getItemDto(item);
    }

    public ItemDto updateItem(ItemDto itemDto){
        checkIfExists(itemDto.getId());
        Item updatedItem = Item.update(itemDto.getId(), itemDto.getName(), itemDto.getType(), itemDto.getDescription(), itemDto.getCost(), itemDto.getCreated_at());
        itemPersistenceSpi.persist(updatedItem);
        return getItemDto(updatedItem);
    }

    public ItemDto deleteItem(String id){
        Item item = itemPersistenceSpi.get(id);
        item.deleteItem();
        Item deletedItem = itemPersistenceSpi.delete(item);
        return getItemDto(deletedItem);
    }

    private void checkIfExists(String id) {
        boolean exists = itemPersistenceSpi.find(id);
        if(!exists){
            throw new RuntimeException(String.format("this item with id: %s does not exist", id));
        }
    }

    public List<ItemDto> getItemsByType(String name) {
        List<Item> items = itemPersistenceSpi.getItemsByType(name);
        return items.stream().map(this::getItemDto).collect(Collectors.toList());
    }

    private ItemDto getItemDto(Item item) {
        return ItemDto.build(item.getItemId().getValue().toString(),
                item.getName().getValue(),
                item.getType().name(),
                item.getDescription(),
                item.getCost().doubleValue(),
                item.getCreated_at(),
                item.getUpdated_at(),
                item.getDeleted_at());
    }
}

