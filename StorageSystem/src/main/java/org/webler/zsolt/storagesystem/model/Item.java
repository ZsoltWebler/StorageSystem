package org.webler.zsolt.storagesystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.StorageType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private ShopCategory category;

    @Embedded
    private UnitQuantity unitQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<ItemDetails> itemDetails = new ArrayList<>();

    public Storage getAvailableStorage() {

        if (itemDetails.size() < 2) {

            if (itemDetails.size() < 1) {
                return null;
            }

            Storage storage = itemDetails.get(0).getStorage();
            if (storage.getType() == StorageType.AVAILABLE) {
                return storage;
            } else {
                return null;
            }
        }
        Storage storage_1 = itemDetails.get(0).getStorage();
        Storage storage_2 = itemDetails.get(1).getStorage();

        return storage_1.getType() == StorageType.AVAILABLE ? storage_1 : storage_2;


    }

    public Storage getTargetStorage() {
        if (itemDetails.size() < 2) {

            if (itemDetails.size() < 1) {
                return null;
            }

            Storage storage = itemDetails.get(0).getStorage();
            if (storage.getType() == StorageType.TARGET) {
                return storage;
            } else {
                return null;
            }
        }
        Storage storage_1 = itemDetails.get(0).getStorage();
        Storage storage_2 = itemDetails.get(1).getStorage();

        return storage_1.getType() == StorageType.TARGET ? storage_1 : storage_2;
    }

    public void add(ItemDetails itemDetails) {
        itemDetails.setItem(this);
        this.itemDetails.add(itemDetails);
    }

    public void remove(ItemDetails itemDetails) {
        itemDetails.setItem(null);
        this.itemDetails.remove(itemDetails);
    }

}
