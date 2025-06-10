package com.example.mas_final_project.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dog extends Animal {
    @NotBlank(message = "Breed group cannot be null or blank")
    @Setter(AccessLevel.PRIVATE)
    private String breedGroup;

    public Dog(String name, int age, Gender gender, boolean isNeuteredOrCastrated, String breedGroup) {
        super(name, age, gender, isNeuteredOrCastrated);
        setBreedGroup(breedGroup);
    }

    public Dog(String name, int age, String race, Gender gender, boolean isNeuteredOrCastrated, String breedGroup) {
        super(name, age, race, gender, isNeuteredOrCastrated);
        setBreedGroup(breedGroup);
    }
}
