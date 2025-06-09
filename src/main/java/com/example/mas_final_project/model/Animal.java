package com.example.mas_final_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be blank or null")
    private String name;

    @Min(0)
    private int age;

    private String race;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean isNeutered;
    private boolean isCastrated;

    public void setRace(String race) {
        if (race != null && race.isBlank()) throw new IllegalArgumentException("Race cannot be blank");
        this.race = race;
    }

    public boolean getIsCastrated() {
        if (gender != Gender.MALE) throw new RuntimeException("Animal is not a male");
        return isCastrated;
    }

    public boolean getIsNeutered() {
        if (gender != Gender.FEMALE) throw new RuntimeException("Animal is not a female");
        return isNeutered;
    }
}
