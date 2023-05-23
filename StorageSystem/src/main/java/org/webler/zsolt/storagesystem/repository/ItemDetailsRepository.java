package org.webler.zsolt.storagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.ItemDetails;
import org.webler.zsolt.storagesystem.model.Storage;

import java.util.Optional;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, Long> {

    Optional<ItemDetails> findByItemAndStorage(Item item, Storage storage);

}
