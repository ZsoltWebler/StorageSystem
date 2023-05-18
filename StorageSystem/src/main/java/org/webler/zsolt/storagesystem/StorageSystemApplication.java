package org.webler.zsolt.storagesystem;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.model.enums.StorageType;
import org.webler.zsolt.storagesystem.repository.StorageRepository;


@SpringBootApplication
public class StorageSystemApplication {

    @Autowired
    private StorageRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(StorageSystemApplication.class, args);
    }

    @Bean
    InitializingBean initDatabase() {
        return () -> {
            repository.save(new Storage(StorageType.AVAILABLE));
            repository.save(new Storage(StorageType.TARGET));
        };
    }

}
