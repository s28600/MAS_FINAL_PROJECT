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
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(0)
    @Setter
    @Getter
    private static double cost = 200;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @OneToMany(mappedBy = "comprisedOf")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> comprises = new HashSet<>();
}
