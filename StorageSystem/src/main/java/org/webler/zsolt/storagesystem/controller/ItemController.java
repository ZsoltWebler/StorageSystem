package org.webler.zsolt.storagesystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemRepository repository;


    @GetMapping
    public List<Item> getItems() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody @Valid Item item) {
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.delete(getItemById(id));
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return getItemById(id);
    }

    private Item getItemById(Long id) throws ResponseStatusException {
        Optional<Item> itemById = repository.findById(id);
        if (itemById.isPresent()) {
            return itemById.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
