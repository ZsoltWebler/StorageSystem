package org.webler.zsolt.storagesystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingList {

    private ShopCategory category;
    private List<ItemDTOWithQuantity> shoppingList = new ArrayList<>();


    public void addItemWithQuantity(ItemDTOWithQuantity items) {
        shoppingList.add(items);
    }

}
