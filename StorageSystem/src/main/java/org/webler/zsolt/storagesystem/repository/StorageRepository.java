package org.webler.zsolt.storagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.enums.StorageType;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Long> {


    Storage getStorageByType(StorageType type);

    @Query("SELECT s FROM Storage AS s WHERE :item IN (SELECT i.item FROM s.itemDetails AS i)")
    public List<Storage> findAllStorageByItemDetails(Item item);

}
