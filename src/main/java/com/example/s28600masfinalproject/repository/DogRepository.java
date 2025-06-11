package com.example.s28600masfinalproject.repository;

import com.example.s28600masfinalproject.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
