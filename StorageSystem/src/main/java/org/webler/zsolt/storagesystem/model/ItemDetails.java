package org.webler.zsolt.storagesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetails {

    @EmbeddedId
    ItemDetailsKey id = new ItemDetailsKey();

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(insertable = false, updatable = false)
    private Item item;

    @ManyToOne
    @MapsId("storageId")
    @JsonIgnore
    @JoinColumn(insertable = false, updatable = false)
    private Storage storage;

    private Double quantity = 0.0;

}
