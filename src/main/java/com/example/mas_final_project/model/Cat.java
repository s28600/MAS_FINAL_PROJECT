package com.example.mas_final_project.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cat extends Animal {
    @NotBlank(message = "Fur length cannot be null or blank")
    @Setter(AccessLevel.PRIVATE)
    private String furLength;

    public Cat(String name, int age, Gender gender, boolean isNeuteredOrCastrated, String furLength) {
        super(name, age, gender, isNeuteredOrCastrated);
        setFurLength(furLength);
    }

    public Cat(String name, int age, String race, Gender gender, boolean isNeuteredOrCastrated, String furLength) {
        super(name, age, race, gender, isNeuteredOrCastrated);
        setFurLength(furLength);
    }
}
