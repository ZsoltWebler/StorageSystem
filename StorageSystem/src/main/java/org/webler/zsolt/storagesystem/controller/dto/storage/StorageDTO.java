package org.webler.zsolt.storagesystem.controller.dto.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTO;
import org.webler.zsolt.storagesystem.model.enums.StorageType;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {

    private StorageType type;
    private HashMap<ItemDTO,Double> items;
}
