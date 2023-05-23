package org.webler.zsolt.storagesystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTOWithQuantity {

    private ItemDTO itemDTO;
    private Double quantity;

}
