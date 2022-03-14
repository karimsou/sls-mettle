package com.example.slsmettle.domain;

import com.example.slsmettle.domain.model.Item;

import java.util.List;


public interface ItemPersistenceSpi {

    public Item get(String id);
    public Item delete(Item item);
    public Item persist(Item item);
    public boolean find(String id);

    List<Item> getItemsByType(String name);
}
