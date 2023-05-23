package org.webler.zsolt.storagesystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperShoppingList {

    private List<ShoppingList> shoppingLists;

}
