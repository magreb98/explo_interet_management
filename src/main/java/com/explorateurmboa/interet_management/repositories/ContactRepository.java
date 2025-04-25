package com.explorateurmboa.interet_management.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.explorateurmboa.interet_management.models.Contact;
@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    
}
