package org.webler.zsolt.storagesystem.repository;


import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.UnitQuantity;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.Unit;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect")
//@ContextConfiguration(classes = {StorageSystemApplication.class, ManageStorageService.class})
public class ItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void findByIdTest() {

        Item item = new Item();
        item.setName("TestItem");
        item.setCategory(ShopCategory.FOOD);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        entityManager.persistAndFlush(item);

        Optional<Item> byId = itemRepository.findById(item.getId());

        assertTrue(byId.isPresent());
        assertEquals(byId.get(), item);

    }

    @Test
    public void findByCategory() {
        Item item = new Item();
        item.setName("TestItem");
        item.setCategory(ShopCategory.TECH);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        entityManager.persistAndFlush(item);

        List<Item> allByCategory = itemRepository.findAllByCategory(item.getCategory());

        assertEquals(allByCategory.size(), 1);
        assertEquals(allByCategory.get(0), item);
        for (Item _item : allByCategory) {
            assertEquals(_item.getCategory(), ShopCategory.TECH);
        }

    }

    @Test
    public void findByName() {
        Item item = new Item();
        item.setName("TestItem");
        item.setCategory(ShopCategory.FOOD);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        entityManager.persistAndFlush(item);

        Optional<Item> byId = itemRepository.findByName(item.getName());

        assertTrue(byId.isPresent());
        assertEquals(byId.get(), item);
    }

    @Test
    public void delete() {
        Item item = new Item();
        item.setName("TestItem");
        item.setCategory(ShopCategory.FOOD);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        entityManager.persistAndFlush(item);

        int prevSize = itemRepository.findAll().size();

        itemRepository.delete(item);

        try {
            Item singleResult = (Item) entityManager.getEntityManager().createQuery("SELECT i FROM Item AS i WHERE i.id = :id")
                    .setParameter("id", item.getId())
                    .getSingleResult();
        } catch (Exception e) {
            assertTrue(e instanceof NoResultException);
        }

        int currSize = itemRepository.findAll().size();

        assertEquals(prevSize - currSize, 1);

    }

}
