package com.explorateurmboa.interet_management.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.explorateurmboa.interet_management.models.Category;
@Repository
public interface CategorieRepository extends CrudRepository<Category, Long>{

    public Category findByname(String name);
}