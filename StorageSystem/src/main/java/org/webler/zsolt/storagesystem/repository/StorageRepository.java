package org.webler.zsolt.storagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.enums.StorageType;

public interface StorageRepository extends JpaRepository<Storage,Long> {


    Storage getStorageByType(StorageType type);

}
