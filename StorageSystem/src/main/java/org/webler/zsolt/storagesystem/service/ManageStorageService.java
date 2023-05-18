package org.webler.zsolt.storagesystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.ItemDetails;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.repository.ItemDetailsRepository;
import org.webler.zsolt.storagesystem.repository.ItemRepository;
import org.webler.zsolt.storagesystem.repository.StorageRepository;

@Service
public class ManageStorageService {


    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    ItemDetailsRepository itemDetailsRepository;

    public Storage addItemToStorage(StorageType storageType, Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        Storage storageByType = storageRepository.getStorageByType(storageType);

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItem(item);
        itemDetails.setStorage(storageByType);
        itemDetails.setQuantity(itemDetails.getQuantity() + 1);
        itemDetailsRepository.save(itemDetails);

        return storageByType;
    }

    public Storage removeItemFromStorage(StorageType storageType, Long itemId) {
        return null;
    }

    public Storage getStorageContentByType(StorageType storageType) {
        return null;
    }


}
