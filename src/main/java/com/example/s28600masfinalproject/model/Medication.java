package com.example.s28600masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be blank or null")
    @Setter(AccessLevel.PRIVATE)
    private String name;

    @Min(1)
    private double costPerUnit;

    @OneToMany(mappedBy = "medication", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Administration> administrations = new HashSet<>();

    @ManyToMany(mappedBy = "prescribed")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Prescription> prescribedIn = new HashSet<>();
}
