package com.example.s28600masfinalproject.repository;

import com.example.s28600masfinalproject.model.Animal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
    List<Animal> findAllByOrderByNameAsc();
}
