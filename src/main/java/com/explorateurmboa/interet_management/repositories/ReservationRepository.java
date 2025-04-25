package com.explorateurmboa.interet_management.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.explorateurmboa.interet_management.models.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
}
