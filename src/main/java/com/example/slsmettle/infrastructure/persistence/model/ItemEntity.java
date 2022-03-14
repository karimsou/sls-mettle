package com.example.slsmettle.infrastructure.persistence.model;

import com.example.slsmettle.domain.model.Name;
import com.example.slsmettle.domain.model.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class ItemEntity {
    @Id
    private String id;
    private String name;
    private String type;
    private String description;
    private Double cost;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    public ItemEntity(String id, String name, String type, String description, Double cost, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime deleted_at) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }
}
