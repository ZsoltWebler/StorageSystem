package org.webler.zsolt.storagesystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTO;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTOConverter;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.repository.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemRepository repository;


    @GetMapping
    public List<ItemDTO> getItems() {
        return repository.findAll().stream().map(ItemDTOConverter::convertToItemDTO).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody @Valid ItemDTO itemDTO) {
        return ItemDTOConverter.convertToItemDTO(repository.save(ItemDTOConverter.convertToItem(itemDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.delete(getItemById(id));
    }

    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable Long id) {
        return ItemDTOConverter.convertToItemDTO(getItemById(id));
    }

    private Item getItemById(Long id) throws ResponseStatusException {
        Optional<Item> itemById = repository.findById(id);
        if (itemById.isPresent()) {
            return itemById.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
