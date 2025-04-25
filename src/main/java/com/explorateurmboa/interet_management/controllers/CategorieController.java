package com.explorateurmboa.interet_management.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.explorateurmboa.interet_management.models.Category;
import com.explorateurmboa.interet_management.repositories.CategorieRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("api/pi/categories")  // Bonne pratique : Pluriel pour les ressources REST
public class CategorieController {

    @Autowired
    private CategorieRepository repository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        try {
            List<Category> categories = (List<Category>) repository.findAll();

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Optional<Category> category = repository.findById(id);

        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        try {
            Category savedCategory = repository.save(category);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Optional<Category> categoryOptional = repository.findById(id);

        if (categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();
            existingCategory.setName(categoryDetails.getName());  // Ajoute d'autres champs si nécessaire
            existingCategory.setUpdatedAt(categoryDetails.getUpdatedAt());

            return new ResponseEntity<>(repository.save(existingCategory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
