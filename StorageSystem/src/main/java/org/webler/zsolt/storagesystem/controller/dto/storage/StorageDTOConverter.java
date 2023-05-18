package org.webler.zsolt.storagesystem.controller.dto.storage;

import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTO;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTOConverter;
import org.webler.zsolt.storagesystem.model.ItemDetails;
import org.webler.zsolt.storagesystem.model.Storage;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StorageDTOConverter {

    public static Storage convertToStorage(StorageDTO storageDTO) throws NoSuchMethodException {

        throw new NoSuchMethodException();
    }

    public static StorageDTO convertToStorageDTO(Storage storage) {
        StorageDTO storageDTO = new StorageDTO();

        storageDTO.setType(storage.getType());
        Map<ItemDTO, Double> collect = storage.getItemDetails().stream()
                .collect(
                        Collectors.toMap(
                                itemDetails -> ItemDTOConverter.convertToItemDTO(itemDetails.getItem()),
                                ItemDetails::getQuantity));

        storageDTO.setItems(new HashMap<>(collect));


        return storageDTO;
    }
}
