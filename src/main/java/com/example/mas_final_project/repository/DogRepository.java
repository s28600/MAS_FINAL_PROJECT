package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
