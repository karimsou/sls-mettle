package com.example.slsmettle.infrastructure.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "deleted_items")
@Data
@NoArgsConstructor
public class DeletedItemEntity {
    @Id
    private String id;
    private String name;
    private String type;
    private String description;
    private Double cost;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    public DeletedItemEntity(String id, String name, String type, String description, Double cost, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime deleted_at) {
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
