package org.webler.zsolt.storagesystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;

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

    public void add(ItemDetails itemDetails) {
        itemDetails.setItem(this);
        this.itemDetails.add(itemDetails);
    }

}
