package com.example.slsmettle.infrastructure.persistence;

import com.example.slsmettle.infrastructure.persistence.model.DeletedItemEntity;
import com.example.slsmettle.infrastructure.persistence.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DeletedItemRepository extends JpaRepository<DeletedItemEntity, String>{

}
