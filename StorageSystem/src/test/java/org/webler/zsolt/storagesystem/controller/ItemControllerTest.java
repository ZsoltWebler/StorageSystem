package org.webler.zsolt.storagesystem.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.webler.zsolt.storagesystem.StorageSystemApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = StorageSystemApplication.class,
        properties = {"spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect", "spring.datasource.url = jdbc:h2:mem:test"})
@AutoConfigureMockMvc
public class ItemControllerTest {

}
