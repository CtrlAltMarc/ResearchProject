package com.plates.cups.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.plates.cups.model.FoodEntity;
import com.plates.cups.service.FoodService;

@RestController
@CrossOrigin
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodService service;

    // TODO: FIX THIS
    // @GetMapping
    // public ResponseEntity<List<FoodEntity>> getAll() {
	//     List<FoodEntity>> list = service.getAll();
	// 	return list;
    // }

    @GetMapping("/{id}")
    public ResponseEntity<FoodEntity> getFmDppdbAorCode(@PathVariable("id") UUID id) {
        Optional<FoodEntity> entity = service.getById(id);
        if (entity.isPresent()) {
            return new ResponseEntity<FoodEntity>(entity.get(), new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<FoodEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<FoodEntity> createOrUpdateBody(@RequestBody FoodEntity dppdbAorCodeEntity) {
        String[] errorMessage = new String[1];
        FoodEntity updated = service.createOrUpdate(dppdbAorCodeEntity, errorMessage);
        return new ResponseEntity<FoodEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
