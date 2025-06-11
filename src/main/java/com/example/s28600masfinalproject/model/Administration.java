package com.example.s28600masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"appointment_id", "medication_id"})
})
@Builder
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(1)
    private int dosageInUnits;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AdministrationRoute route;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Appointment appointment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Medication medication;
}
