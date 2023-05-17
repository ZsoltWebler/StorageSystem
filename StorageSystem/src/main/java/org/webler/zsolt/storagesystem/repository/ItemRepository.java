package org.webler.zsolt.storagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webler.zsolt.storagesystem.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
