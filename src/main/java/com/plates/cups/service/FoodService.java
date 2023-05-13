package com.plates.cups.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plates.cups.model.FoodEntity;
import com.plates.cups.repository.FoodRepository;

@Service
public class FoodService {

    @Autowired
    FoodRepository repository;

    public List<FoodEntity> getAll() {
		return repository.findAll();
	}

    public Optional<FoodEntity> getById(UUID id) {
		return repository.findById(id);
	}

    public FoodEntity createOrUpdate(FoodEntity entity, String[] errorMessage) { 
        // Lines added to workaround issue that forces id to be set to a non null value
		if (entity.getId() == null) {
			entity.setId(getNewUUID());
		}

		Optional<FoodEntity> foundEntity = repository.findById(entity.getId());
        if (foundEntity.isPresent()) {
			FoodEntity existingEntity = foundEntity.get();

            existingEntity.setName(entity.getName());
            existingEntity.setDescription(entity.getDescription());
            existingEntity = repository.save(existingEntity);

            errorMessage[0] += "Updated an existing entity\n";
			return existingEntity;
		} else {
			FoodEntity newEntity = repository.save(entity);
			errorMessage[0] += "Created a new entity\n";
			return newEntity;
		}
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public UUID getNewUUID() {
		UUID newUUID = UUID.randomUUID();
		if(getById(newUUID).isPresent()) {
			return getNewUUID();
		}
		return newUUID;
	}
}
