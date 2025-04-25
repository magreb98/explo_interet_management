package com.explorateurmboa.interet_management.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.explorateurmboa.interet_management.models.PointInteret;

@Repository
public interface PointInteretRepository extends CrudRepository<PointInteret, Long> {
    
}
