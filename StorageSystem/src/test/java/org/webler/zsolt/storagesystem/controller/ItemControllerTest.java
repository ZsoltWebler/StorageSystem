package org.webler.zsolt.storagesystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.webler.zsolt.storagesystem.StorageSystemApplication;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTO;
import org.webler.zsolt.storagesystem.controller.dto.item.ItemDTOConverter;
import org.webler.zsolt.storagesystem.model.Item;
import org.webler.zsolt.storagesystem.model.UnitQuantity;
import org.webler.zsolt.storagesystem.model.enums.ShopCategory;
import org.webler.zsolt.storagesystem.model.enums.Unit;
import org.webler.zsolt.storagesystem.repository.ItemRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = StorageSystemApplication.class,
        properties = {"spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect", "spring.datasource.url = jdbc:h2:mem:test"})
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepository itemRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createItemTest() throws Exception {

        Item item = new Item();
        item.setId(100L);
        item.setName("TestItem");
        item.setCategory(ShopCategory.FOOD);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        Mockito.when(itemRepository.save(any())).thenReturn(item);

        ItemDTO itemDTO = ItemDTOConverter.convertToItemDTO(item);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(itemDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(itemDTO)));
    }

    @Test
    public void getByIdTest() throws Exception {

        Item item = new Item();
        item.setId(100L);
        item.setName("TestItem");
        item.setCategory(ShopCategory.FOOD);
        item.setUnitQuantity(new UnitQuantity(Unit.PIECE, 1.0));

        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(item));

        ItemDTO itemDTO = ItemDTOConverter.convertToItemDTO(item);

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(itemDTO)));
    }

}
