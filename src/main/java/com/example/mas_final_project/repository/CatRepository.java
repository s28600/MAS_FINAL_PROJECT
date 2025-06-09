package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, Long> {
}
