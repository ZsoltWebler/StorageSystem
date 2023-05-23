package org.webler.zsolt.storagesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.model.enums.StorageType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", unique = true)
    private StorageType type;

    @OneToMany
    private List<ItemDetails> itemDetails = new ArrayList<>();

    public void add(ItemDetails itemDetails) {
            itemDetails.setStorage(this);
            this.itemDetails.add(itemDetails);
    }

    public void remove(ItemDetails itemDetails) {
        itemDetails.setItem(null);
        this.itemDetails.remove(itemDetails);
    }

    public Storage(StorageType type) {
        this.type = type;
    }
}
