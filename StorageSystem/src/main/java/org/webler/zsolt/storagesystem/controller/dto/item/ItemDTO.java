package org.webler.zsolt.storagesystem.controller.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.model.UnitQuantity;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;
    private String name;
    private ShopCategory category;
    private UnitQuantity unitQuantity;

}
