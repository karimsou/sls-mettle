package com.example.slsmettle.infrastructure.persistence;

import com.example.slsmettle.domain.model.Item;
import com.example.slsmettle.domain.ItemPersistenceSpi;
import com.example.slsmettle.infrastructure.persistence.model.DeletedItemEntity;
import com.example.slsmettle.infrastructure.persistence.model.ItemEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemPersistenceService implements ItemPersistenceSpi {

    private final ItemRepository itemRepository;
    private final DeletedItemRepository deletedItemRepository;

    public ItemPersistenceService(ItemRepository itemRepository, DeletedItemRepository deletedItemRepository) {
        this.itemRepository = itemRepository;
        this.deletedItemRepository = deletedItemRepository;
    }

    @Override
    public Item get(String id) {
        try {
            ItemEntity itemEntity = itemRepository.getById(id);
            return getItem(itemEntity);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("item with id: %s does not exist", id));
        }
    }

    public Item delete(Item item) {
        DeletedItemEntity deletedItemEntity = new DeletedItemEntity(item.getItemId().getValue().toString(), item.getName().getValue(),
                item.getType().name(), item.getDescription(), item.getCost().doubleValue(),
                item.getCreated_at(), item.getUpdated_at(), item.getDeleted_at());
        deletedItemRepository.save(deletedItemEntity);
        itemRepository.deleteById(item.getItemId().getValue().toString());
        return item;
    }

    @Override
    public Item persist(Item item) {
        ItemEntity itemEntity = new ItemEntity(item.getItemId().getValue().toString(), item.getName().getValue(), item.getType().name(),
                item.getDescription(), item.getCost().doubleValue(), item.getCreated_at(), item.getUpdated_at(), item.getDeleted_at());
        ItemEntity savedItem = itemRepository.save(itemEntity);
        return getItem(savedItem);
    }

    @Override
    public boolean find(String id) {
        return itemRepository.existsById(id);
    }

    @Override
    public List<Item> getItemsByType(String name) {
        List<ItemEntity> items = itemRepository.findByType(name);
        return items.stream().map(this::getItem).collect(Collectors.toList());
    }

    private Item getItem(ItemEntity itemEntity) {
        return Item.get(itemEntity.getId(),
                itemEntity.getName(),
                itemEntity.getType(),
                itemEntity.getDescription(),
                itemEntity.getCost(),
                itemEntity.getCreated_at(),
                itemEntity.getUpdated_at(),
                itemEntity.getDeleted_at());
    }
}
