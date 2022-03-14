package com.example.slsmettle.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
public class Item {

    private ItemId itemId;
    private Name name;
    private Type type;
    private String description;
    private BigDecimal cost;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    private Item(String id, String name, String type, String description, Double cost) {
        this.itemId = ItemId.of(UUID.randomUUID());
        initItem(name, description, cost, type);
        this.created_at = LocalDateTime.now();
    }

    private Item(String id, String name, String type, String description, Double cost, LocalDateTime created_at) {
        this.itemId = ItemId.of(UUID.fromString(id));
        initItem(name, description, cost, type);
        this.updated_at = LocalDateTime.now();
        this.created_at = created_at;
    }

    private void initItem(String name, String description, Double cost, String type) {
        Optional.ofNullable(name).filter(n -> n.length() <= 20)
                .ifPresentOrElse(n -> this.name = Name.of(n), () -> {
                    throw new RuntimeException("Item Name should not exceed 20 characters");
                });
        Optional.ofNullable(description).filter(d -> d.length() <= 200)
                .ifPresentOrElse(d -> this.description = d, () -> {
                    throw new RuntimeException("Item Description should not exceed 200 characters");
                });
        Optional.ofNullable(cost).filter(c -> BigDecimal.valueOf(c).compareTo(BigDecimal.ZERO) > 0)
                .ifPresentOrElse(c -> this.cost = BigDecimal.valueOf(c), () -> {
                    throw new RuntimeException("Item Cost should be greater than 0");
                });
        this.type = Type.of(type);
    }

    private Item(String id, String name, String type, String description,
                 Double cost, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime deleted_at) {
        this.itemId = ItemId.of(UUID.fromString(id));
        this.name = Name.of(name);
        this.description = description;
        this.type = Type.of(type);
        this.cost = BigDecimal.valueOf(cost);
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public static Item get(String id, String name, String type, String description,
                           Double cost, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime deleted_at) {
        return new Item(id, name, type, description, cost, created_at, updated_at, deleted_at);
    }

    public static Item create(String name, String type, String description,
                              Double cost) {
        return new Item(null, name, type, description, cost);
    }

    public static Item update(String id, String name, String type, String description,
                              Double cost, LocalDateTime created_at) {
        return new Item(id, name, type, description, cost, created_at);
    }

    public Item deleteItem() {
        if (Objects.isNull(deleted_at)) {
            this.deleted_at = LocalDateTime.now();
        }
        return this;
    }
}
