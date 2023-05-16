package org.webler.zsolt.storagesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.model.enums.Unit;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnitQuantity {

    @Enumerated(EnumType.STRING)
    private Unit unit;
    
    private Double quantity;
}
