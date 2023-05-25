package org.webler.zsolt.storagesystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.ItemDetails;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.UnitQuantity;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.model.enums.Unit;
import org.webler.zsolt.storagesystem.repository.ItemDetailsRepository;
import org.webler.zsolt.storagesystem.repository.ItemRepository;
import org.webler.zsolt.storagesystem.repository.StorageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class ManageStorageServiceTest {

    @TestConfiguration
    static class ManageStorageServiceTestContextConfiguration {

        @Bean
        public ManageStorageService manageStorageService() {
            return new ManageStorageService();
        }

    }

    @Autowired
    ManageStorageService manageStorageService;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    StorageRepository storageRepository;

    @MockBean
    ItemDetailsRepository itemDetailsRepository;

    @Test
    public void addItemToStorageTestWhenStorageDetailsNotPresents() {

        Item item = new Item();
        item.setId(100L);
        item.setName("TestItem");
        item.setCategory(ShopCategory.FOOD);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        Storage storage = new Storage();
        storage.setId(100L);
        storage.setType(StorageType.AVAILABLE);

        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(storageRepository.getStorageByType(any())).thenReturn(storage);
        Mockito.when(itemDetailsRepository.findByItemAndStorage(any(), any())).thenReturn(Optional.empty());

        Storage storageWithItem = manageStorageService.addItemToStorage(StorageType.AVAILABLE, 100L);

        assertEquals(storageWithItem.getItemDetails().size(), 1);
        assertEquals(storageWithItem.getItemDetails().get(0).getItem().getName(), item.getName());

    }

    @Test
    public void addItemToStorageTestWhenStorageDetailsPresents() {

        Item item = new Item();
        item.setId(100L);
        item.setName("TestItem");
        item.setCategory(ShopCategory.FOOD);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        Storage storage = new Storage();
        storage.setId(100L);
        storage.setType(StorageType.AVAILABLE);

        ItemDetails detailsMock = mock(ItemDetails.class, "detailsMock");

        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(storageRepository.getStorageByType(any())).thenReturn(storage);
        Mockito.when(itemDetailsRepository.findByItemAndStorage(any(), any())).thenReturn(Optional.of(detailsMock));

        manageStorageService.addItemToStorage(StorageType.AVAILABLE, 100L);

        Mockito.verify(detailsMock,Mockito.times(1)).setQuantity(1.0);


    }


}
