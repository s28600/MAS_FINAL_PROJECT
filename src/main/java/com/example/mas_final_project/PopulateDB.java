package com.example.mas_final_project;

import com.example.mas_final_project.model.Cat;
import com.example.mas_final_project.model.Gender;
import com.example.mas_final_project.repository.AnimalRepository;
import com.example.mas_final_project.repository.CatRepository;
import com.example.mas_final_project.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PopulateDB {
    private final AnimalRepository animalRepository;
    private final CatRepository catRepository;
    private final DogRepository dogRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent event){
        if (animalRepository.count() == 0){
            animalRepository.save(new Cat("Cat1", 2, Gender.FEMALE, false, "Long"));
            System.out.println("Animal repository populated");
        }
    }
}
