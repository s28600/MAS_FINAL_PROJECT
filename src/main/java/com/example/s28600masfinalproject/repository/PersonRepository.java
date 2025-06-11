package com.example.s28600masfinalproject.repository;

import com.example.s28600masfinalproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public Person findByName(String Name);
    @Query("SELECT p FROM Person p JOIN p.personType pt WHERE pt = com.example.s28600masfinalproject.model.PersonType.CLIENT")
    List<Person> findAllClients();
}
