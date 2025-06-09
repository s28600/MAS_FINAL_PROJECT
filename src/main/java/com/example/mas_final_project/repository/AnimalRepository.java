package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

}
