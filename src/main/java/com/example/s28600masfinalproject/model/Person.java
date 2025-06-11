package com.example.s28600masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    @NotBlank(message = "Surname cannot be null or blank")
    private String surname;

    @NotBlank(message = "Pesel cannot be null or blank")
    @Pattern(regexp = "\\d{11}", message = "Pesel must consist of 11 digits")
    @Column(unique = true)
    private String pesel;

    @NotEmpty(message = "Person has to have at least one role")
    @ElementCollection(targetClass = PersonType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "person_type", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "type")
    private Set<PersonType> personType;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String address;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String specialization;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int bookedAppointments;

    @OneToMany(mappedBy = "ownedBy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Set<Animal> owns = new HashSet<>();

    @OneToMany(mappedBy = "appointedPerson")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> appointedFor = new HashSet<>();

    @OneToMany(mappedBy = "conductedBy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> conducts = new HashSet<>();

    public String getAddress() {
        if (!personType.contains(PersonType.CLIENT))
            throw new UnsupportedOperationException("Person is not a client");
        return address;
    }

    public void setAddress(String address) {
        if (!personType.contains(PersonType.CLIENT))
            throw new UnsupportedOperationException("Person is not a client");
        if (address == null || address.isBlank())
            throw new IllegalArgumentException("Address cannot be null or blank");
        this.address = address;
    }

    public String getSpecialization() {
        if (!personType.contains(PersonType.DOCTOR))
            throw new UnsupportedOperationException("Person is not a doctor");
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (!personType.contains(PersonType.DOCTOR))
            throw new UnsupportedOperationException("Person is not a doctor");
        if (specialization == null || specialization.isBlank())
            throw new IllegalArgumentException("Specialization cannot be null or blank");
        this.specialization = specialization;
    }

    public int getBookedAppointments() {
        if (!personType.contains(PersonType.RECEPTIONIST))
            throw new UnsupportedOperationException("Person is not a receptionist");
        return bookedAppointments;
    }

    public void setBookedAppointments(int bookedAppointments) {
        if (!personType.contains(PersonType.RECEPTIONIST))
            throw new UnsupportedOperationException("Person is not a receptionist");
        if (bookedAppointments < 0)
            throw new IllegalArgumentException("Booked appointments number cannot be negative");
        this.bookedAppointments = bookedAppointments;
    }

    public Set<Animal> getOwns() {
        if (!personType.contains(PersonType.CLIENT))
            throw new UnsupportedOperationException("Person is not a client");
        return owns;
    }

    public void setOwns(Set<Animal> owns) {
        if (owns == null || owns.isEmpty())
            throw new IllegalArgumentException("Owns cannot be null or empty");
        if (!personType.contains(PersonType.CLIENT))
            throw new UnsupportedOperationException("Person is not a client");
        for (Animal animal : owns) {
            if (animal == null)
                throw new IllegalArgumentException("Animal cannot be null");
            if (animal.getOwnedBy() != this)
                throw new IllegalArgumentException("Animal already owned by different person");
        }
        this.owns = owns;
    }

    public void changePersonType(Set<PersonType> personType) {
        setPersonType(personType);
    }
}
