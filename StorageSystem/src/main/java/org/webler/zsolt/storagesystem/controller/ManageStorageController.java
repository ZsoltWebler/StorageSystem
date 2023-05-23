package org.webler.zsolt.storagesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webler.zsolt.storagesystem.controller.dto.ShoppingList;
import org.webler.zsolt.storagesystem.controller.dto.SuperShoppingList;
import org.webler.zsolt.storagesystem.controller.dto.storage.StorageDTO;
import org.webler.zsolt.storagesystem.controller.dto.storage.StorageDTOConverter;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.service.ManageStorageService;

@RestController
@RequestMapping("/manage")
public class ManageStorageController {


    @Autowired
    ManageStorageService manageStorageService;

    @PutMapping("/{storageType}/{itemId}")
    public StorageDTO addItemToStorage(@PathVariable String storageType, @PathVariable Long itemId) {
        return StorageDTOConverter.convertToStorageDTO(
                manageStorageService.addItemToStorage(StorageType.valueOf(storageType), itemId)
        );
    }

    @PutMapping("/{storageType}/{itemId}/{quantity}")
    public StorageDTO addItemToStorageWithQuantity(@PathVariable String storageType, @PathVariable Long itemId, @PathVariable Integer quantity) {
        return StorageDTOConverter.convertToStorageDTO(
                manageStorageService.addItemToStorage(StorageType.valueOf(storageType), itemId, quantity)
        );
    }

    @DeleteMapping("/{storageType}/{itemId}")
    public StorageDTO removeItemFromStorage(@PathVariable String storageType, @PathVariable Long itemId) {
        return StorageDTOConverter.convertToStorageDTO(
                manageStorageService.removeItemFromStorage(StorageType.valueOf(storageType), itemId)
        );
    }

    @GetMapping("/{storageType}")
    public StorageDTO getStorageContentByType(@PathVariable String storageType) {
        return StorageDTOConverter.convertToStorageDTO(
                manageStorageService.getStorageContentByType(StorageType.valueOf(storageType))
        );
    }

    @GetMapping("/shopping")
    public SuperShoppingList getShoppingList() {
        return manageStorageService.getShoppingList();
    }

    @GetMapping("/shopping/{shoppingCategory}")
    public ShoppingList getShoppingListByCategory(@PathVariable String shoppingCategory) {
        return manageStorageService.getShoppingListForCategory(ShopCategory.valueOf(shoppingCategory));
    }

}
