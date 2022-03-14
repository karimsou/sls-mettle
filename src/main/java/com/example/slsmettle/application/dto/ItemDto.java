package com.example.slsmettle.application.dto;

import com.example.slsmettle.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String id;
    private String name;
    private String type;
    private String description;
    private Double cost;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    public static ItemDto build(String id, String name, String type, String description,
                             Double cost, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime deleted_at){
        return new ItemDto(id, name, type, description, cost, created_at, updated_at, deleted_at);
    }
}
