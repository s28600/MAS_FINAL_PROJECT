package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Cat;
import com.example.mas_final_project.model.Dog;
import com.example.mas_final_project.model.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private CatRepository catRepository;
    @Autowired
    private DogRepository dogRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Cat cat1, cat2;
    Dog dog1, dog2;

    @BeforeEach
    public void initData() {
        cat1 = new Cat("Cat1", 2, Gender.FEMALE, false, "Long");
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(animalRepository);
        assertNotNull(catRepository);
        assertNotNull(dogRepository);
    }

    @Test
    public void testSaveAll(){
        catRepository.save(cat1);
        entityManager.flush();
        assertEquals(1, animalRepository.count());
    }
}