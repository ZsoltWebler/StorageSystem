package org.webler.zsolt.storagesystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webler.zsolt.storagesystem.model.Storage;
import org.webler.zsolt.storagesystem.repository.StorageRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/storages")
public class StorageController {

    @Autowired
    StorageRepository repository;

    @GetMapping
    public List<Storage> getStorages() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Storage createStorage(@RequestBody @Valid Storage item) {
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.delete(getStorageById(id));
    }

    @GetMapping("/{id}")
    public Storage getStorage(@PathVariable Long id) {
        return getStorageById(id);
    }

    private Storage getStorageById(Long id) throws ResponseStatusException {
        Optional<Storage> storageById = repository.findById(id);
        if (storageById.isPresent()) {
            return storageById.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
