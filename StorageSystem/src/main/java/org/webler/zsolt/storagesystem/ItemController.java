package org.webler.zsolt.storagesystem;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.UnitQuantity;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.Unit;
import org.webler.zsolt.storagesystem.repository.ItemRepository;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemRepository repository;


    @GetMapping
    public List<Item> getItems(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody @Valid Item item){
        return repository.save(item);
    }

}
