package org.webler.zsolt.storagesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.service.ManageStorageService;

@RestController
@RequestMapping("/manage")
public class ManageStorageController {


    @Autowired
    ManageStorageService manageStorageService;

    @PutMapping("/{storageType}/{itemId}")
    public Storage addItemToStorage(@PathVariable String storageType, @PathVariable Long itemId) {
        return manageStorageService.addItemToStorage(StorageType.valueOf(storageType),itemId);
    }

    @DeleteMapping("/{storage_type}/{item_id}")
    public Storage removeItemFromStorage(@PathVariable StorageType storageType, @PathVariable Long itemId) {
        return null;
    }

    @GetMapping("/{storage_type}")
    public Storage getStorageContentByType(@PathVariable StorageType storageType) {
        return null;
    }

}
