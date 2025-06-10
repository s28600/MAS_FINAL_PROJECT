package com.example.mas_final_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be blank or null")
    private String name;

    @Min(1)
    private int age;

    @Setter(AccessLevel.PRIVATE)
    private String race;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.PRIVATE)
    private Gender gender;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private boolean isNeutered;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private boolean isCastrated;

    public Animal(String name, int age, Gender gender, boolean isNeuteredOrCastrated) {
        setName(name);
        setAge(age);
        setGender(gender);
        if(gender == Gender.MALE) setCastrated(isNeuteredOrCastrated);
        else setNeutered(isNeuteredOrCastrated);
    }

    public Animal(String name, int age, String race, Gender gender, boolean isNeuteredOrCastrated) {
        this(name, age, gender, isNeuteredOrCastrated);
        setRace(race);
    }

    public void setRace(String race) {
        if (race != null && race.isBlank()) throw new IllegalArgumentException("Race cannot be blank");
        this.race = race;
    }

    public void setNeutered(boolean neutered) {
        if (gender != Gender.FEMALE) throw new RuntimeException("Animal is not a female");
        isNeutered = neutered;
    }

    public boolean IsNeutered() {
        if (gender != Gender.FEMALE) throw new RuntimeException("Animal is not a female");
        return isNeutered;
    }

    public void setCastrated(boolean castrated) {
        if (gender != Gender.MALE) throw new RuntimeException("Animal is not a male");
        isCastrated = castrated;
    }

    public boolean IsCastrated() {
        if (gender != Gender.MALE) throw new RuntimeException("Animal is not a male");
        return isCastrated;
    }
}
