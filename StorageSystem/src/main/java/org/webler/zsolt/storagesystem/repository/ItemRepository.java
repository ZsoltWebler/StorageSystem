package org.webler.zsolt.storagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webler.zsolt.storagesystem.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
