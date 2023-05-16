package org.webler.zsolt.storagesystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class ItemDetailsKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 7573790735407535286L;

    @Column(name = "item_id")
    Long itemId;

    @Column(name = "storage_id")
    Long storageId;
}
