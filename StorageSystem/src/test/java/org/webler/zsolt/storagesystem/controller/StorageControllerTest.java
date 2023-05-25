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
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.repository.StorageRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = StorageSystemApplication.class,
        properties = {"spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect", "spring.datasource.url = jdbc:h2:mem:test"})
@AutoConfigureMockMvc
public class StorageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StorageRepository storageRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createStorageTest() throws Exception {

        Storage storage = new Storage();
        storage.setId(100L);
        storage.setType(StorageType.AVAILABLE);

        Mockito.when(storageRepository.save(any())).thenReturn(storage);


        mockMvc.perform(post("/storages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(storage)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(storage),false));
    }

    @Test
    public void getByIdTest() throws Exception {

        Storage storage = new Storage();
        storage.setId(100L);
        storage.setType(StorageType.AVAILABLE);

        Mockito.when(storageRepository.findById(any())).thenReturn(Optional.of(storage));


        mockMvc.perform(get("/storages/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(storage)));
    }

}
