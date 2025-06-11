package com.example.s28600masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @Setter(AccessLevel.NONE)
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private Person ownedBy;

    @OneToMany(mappedBy = "treatedAnimal")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> treatedDuring = new HashSet<>();

    public Animal(String name, Person ownedBy, int age, Gender gender, boolean isNeuteredOrCastrated) {
        setName(name);
        setOwnedBy(ownedBy);
        setAge(age);
        setGender(gender);
        if(gender == Gender.MALE) setCastrated(isNeuteredOrCastrated);
        else setNeutered(isNeuteredOrCastrated);
    }

    public Animal(String name, Person ownedBy, int age, String race, Gender gender, boolean isNeuteredOrCastrated) {
        this(name, ownedBy, age, gender, isNeuteredOrCastrated);
        setRace(race);
    }

    private void setRace(String race) {
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

    public void setOwnedBy(Person ownedBy) {
        if (ownedBy == null )
            throw new IllegalArgumentException("Owner cannot be null and has to be a client");
        if (!ownedBy.getPersonType().contains(PersonType.CLIENT))
            throw new UnsupportedOperationException("Owner has to be a client");
        this.ownedBy = ownedBy;
    }
}
