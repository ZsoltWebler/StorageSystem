package org.webler.zsolt.storagesystem.controller.dto.item;

import org.webler.zsolt.storagesystem.model.Item;

public class ItemDTOConverter {


    public static Item convertToItem(ItemDTO itemDTO) {
        Item item = new Item();

        item.setName(itemDTO.getName());
        item.setCategory(itemDTO.getCategory());
        item.setUnitQuantity(itemDTO.getUnitQuantity());

        return item;
    }

    public static ItemDTO convertToItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setName(item.getName());
        itemDTO.setCategory(item.getCategory());
        itemDTO.setUnitQuantity(item.getUnitQuantity());

        return itemDTO;
    }

}
