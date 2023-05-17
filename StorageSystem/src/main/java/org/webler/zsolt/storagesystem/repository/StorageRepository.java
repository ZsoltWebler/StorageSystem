package org.webler.zsolt.storagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webler.zsolt.storagesystem.model.Storage;

public interface StorageRepository extends JpaRepository<Storage,Long> {
}
