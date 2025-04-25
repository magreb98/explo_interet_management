package com.explorateurmboa.interet_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.explorateurmboa.interet_management.models.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
}
