package org.webler.zsolt.storagesystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webler.zsolt.storagesystem.controller.dto.ItemDTOWithQuantity;
import org.webler.zsolt.storagesystem.controller.dto.ShoppingList;
import org.webler.zsolt.storagesystem.controller.dto.SuperShoppingList;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTOConverter;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.ItemDetails;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.repository.ItemDetailsRepository;
import org.webler.zsolt.storagesystem.repository.ItemRepository;
import org.webler.zsolt.storagesystem.repository.StorageRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ManageStorageService {


    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    ItemDetailsRepository itemDetailsRepository;

    public Storage addItemToStorage(StorageType storageType, Long itemId) {
        return addItemToStorage(storageType, itemId, 1);
    }

    public Storage addItemToStorage(StorageType storageType, Long itemId, int quantity) {


        Item item = itemRepository.findById(itemId).orElseThrow();
        Storage storageByType = storageRepository.getStorageByType(storageType);

        Optional<ItemDetails> byItemAndStorage = itemDetailsRepository.findByItemAndStorage(item, storageByType);

        if (byItemAndStorage.isPresent()) {
            ItemDetails itemDetails = byItemAndStorage.get();
            itemDetails.setQuantity(itemDetails.getQuantity() + quantity);

            itemDetailsRepository.saveAndFlush(itemDetails);

            return storageByType;
        }


        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setQuantity(itemDetails.getQuantity() + quantity);

        storageByType.add(itemDetails);
        item.add(itemDetails);

        itemDetailsRepository.saveAndFlush(itemDetails);

        return storageByType;
    }

    public Storage removeItemFromStorage(StorageType storageType, Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow();
        Storage storageByType = storageRepository.getStorageByType(storageType);

        ItemDetails itemDetails = itemDetailsRepository.findByItemAndStorage(item, storageByType).orElseThrow();

        storageByType.remove(itemDetails);
        item.remove(itemDetails);

        itemDetailsRepository.delete(itemDetails);


        return storageByType;
    }

    public Storage getStorageContentByType(StorageType storageType) {

        return storageRepository.getStorageByType(storageType);
    }


    public ShoppingList getShoppingListForCategory(ShopCategory category) {

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setCategory(category);

        Storage targetStorage = getStorageContentByType(StorageType.TARGET);
        Storage availableStorage = getStorageContentByType(StorageType.AVAILABLE);

        List<Item> targetItems = targetStorage.getItemDetails().stream()
                .map(ItemDetails::getItem)
                .filter(item -> item.getCategory().equals(category))
                .toList();
        List<Item> availableItems = availableStorage.getItemDetails().stream()
                .map(ItemDetails::getItem)
                .filter(item -> item.getCategory().equals(category))
                .toList();

        for (Item targetItem : targetItems) {

            if (targetItem.getAvailableStorage() != null) {
                Double availableQuantity = targetItem.getItemDetails()
                        .stream()
                        .filter(itemDet -> itemDet.getStorage().getType().equals(StorageType.AVAILABLE))
                        .findFirst()
                        .get().getQuantity();

                Double targetQuantity = targetItem.getItemDetails()
                        .stream()
                        .filter(itemDet -> itemDet.getStorage().getType().equals(StorageType.TARGET))
                        .findFirst()
                        .get().getQuantity();

                if (targetQuantity > availableQuantity) {
                    shoppingList.addItemWithQuantity(new ItemDTOWithQuantity(
                            ItemDTOConverter.convertToItemDTO(targetItem),
                            targetQuantity - availableQuantity
                    ));

                }

            } else {

                Double targetQuantity = targetItem.getItemDetails()
                        .stream()
                        .filter(itemDet -> itemDet.getStorage().getType().equals(StorageType.TARGET))
                        .findFirst()
                        .get().getQuantity();

                shoppingList.addItemWithQuantity(new ItemDTOWithQuantity(
                        ItemDTOConverter.convertToItemDTO(targetItem),
                        targetQuantity
                ));
            }
        }

        return shoppingList;
    }


    public SuperShoppingList getShoppingList() {

        SuperShoppingList superShoppingList = new SuperShoppingList();
        List<ShoppingList> shoppingLists = new ArrayList<>();

        Arrays.stream(ShopCategory.values()).forEach(category -> {
                    shoppingLists.add(getShoppingListForCategory(category));
                }
        );

        superShoppingList.setShoppingLists(shoppingLists);

        return superShoppingList;
    }
}
