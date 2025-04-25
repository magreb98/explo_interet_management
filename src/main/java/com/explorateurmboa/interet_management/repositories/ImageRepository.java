package com.explorateurmboa.interet_management.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.explorateurmboa.interet_management.models.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long>{

    Optional<Image> findByFileName(String fileName);
}