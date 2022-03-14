package com.example.slsmettle.infrastructure.persistence;

import com.example.slsmettle.infrastructure.persistence.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemRepository extends JpaRepository<ItemEntity, String>{

    List<ItemEntity> findByType(String name);
}
