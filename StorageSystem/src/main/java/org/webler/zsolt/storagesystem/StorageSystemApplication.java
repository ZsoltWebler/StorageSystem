package org.webler.zsolt.storagesystem;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.UnitQuantity;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.model.enums.Unit;
import org.webler.zsolt.storagesystem.repository.ItemRepository;
import org.webler.zsolt.storagesystem.repository.StorageRepository;


@SpringBootApplication
public class StorageSystemApplication {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(StorageSystemApplication.class, args);
    }

    @Bean
    InitializingBean initDatabase() {
        return () -> {
            storageRepository.saveAndFlush(new Storage(StorageType.AVAILABLE));
            storageRepository.saveAndFlush(new Storage(StorageType.TARGET));

            Item appleJuice = new Item();
            appleJuice.setCategory(ShopCategory.FOOD);
            appleJuice.setName("Almalé");
            appleJuice.setUnitQuantity(new UnitQuantity(Unit.LITRE,1.5));
            itemRepository.saveAndFlush(appleJuice);

            Item pearJuice = new Item();
            pearJuice.setCategory(ShopCategory.FOOD);
            pearJuice.setName("Körtelé");
            pearJuice.setUnitQuantity(new UnitQuantity(Unit.LITRE,1.0));
            itemRepository.saveAndFlush(pearJuice);

            Item socks = new Item();
            socks.setCategory(ShopCategory.CLOTHES);
            socks.setName("Zokni");
            socks.setUnitQuantity(new UnitQuantity(Unit.PIECE,1.0));
            itemRepository.saveAndFlush(socks);
        };
    }

}
