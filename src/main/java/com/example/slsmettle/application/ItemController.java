package com.example.slsmettle.application;


import com.example.slsmettle.application.dto.ItemDto;
import com.example.slsmettle.application.error.ErrorMessage;
import com.example.slsmettle.application.filter.TransactionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final static Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);

    private final ItemApplicationService itemService;

    public ItemController(ItemApplicationService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto getItem(@PathVariable String id) {
        LOG.info("get item with id: {}", id);
        return itemService.getItem(id);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItemsByName(@RequestParam String type) {
        LOG.info("search item with type: {}", type);
        return itemService.getItemsByType(type);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto createItem(@RequestBody ItemDto itemDto) {
        LOG.info("create item with body: {}", itemDto);
        return itemService.createItem(itemDto);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto updateItem(@RequestBody ItemDto itemDto) {
        LOG.info("update item with body: {}", itemDto);
        return itemService.updateItem(itemDto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto deleteItem(@PathVariable String id) {
        LOG.info("delete item with body: {}", id);
        return itemService.deleteItem(id);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorMessage> handleException(RuntimeException ex) {
        LOG.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of(ex.getMessage()));
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ErrorMessage> handleException(ResponseStatusException ex) {
        LOG.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getStatus()).body(ErrorMessage.of(ex.getMessage()));
    }
}
