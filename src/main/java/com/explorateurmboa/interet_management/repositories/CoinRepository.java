package com.explorateurmboa.interet_management.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.explorateurmboa.interet_management.models.Coin;

@Repository
public interface CoinRepository extends CrudRepository<Coin, Long> {}