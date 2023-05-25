package org.webler.zsolt.storagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    List<Item> findAllByCategory(ShopCategory category);

    Optional<Item> findByName(String name);
}
