package com.example.s28600masfinalproject.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cat extends Animal {
    @NotBlank(message = "Fur length cannot be null or blank")
    @Setter(AccessLevel.PRIVATE)
    private String furLength;

    public Cat(String name, Person ownedBy, int age, Gender gender, boolean isNeuteredOrCastrated, String furLength) {
        super(name, ownedBy, age, gender, isNeuteredOrCastrated);
        setFurLength(furLength);
    }

    public Cat(String name, Person ownedBy, int age, String race, Gender gender, boolean isNeuteredOrCastrated, String furLength) {
        super(name, ownedBy, age, race, gender, isNeuteredOrCastrated);
        setFurLength(furLength);
    }
}
