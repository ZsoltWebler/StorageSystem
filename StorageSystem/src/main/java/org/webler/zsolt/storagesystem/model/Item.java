package org.webler.zsolt.storagesystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;

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

    @OneToMany(mappedBy = "item")
    private List<ItemDetails> itemDetails;

}
