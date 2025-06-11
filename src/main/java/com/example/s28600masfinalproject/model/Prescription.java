package com.example.s28600masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter(AccessLevel.NONE)
    private LocalDateTime fromDate;

    @Setter(AccessLevel.NONE)
    private LocalDateTime toDate;

    @NotBlank(message = "Code cannot be blank or null")
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Value must contain only letters and numbers")
    private String code;

    @ManyToMany
    @JoinTable(
            name = "prescription_medication",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Medication> prescribed = new HashSet<>();

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "appointment_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Appointment issuedDuring;

    private void setFromDate(LocalDateTime fromDate) {
        if (fromDate == null) throw new IllegalArgumentException("fromDate cannot be null");
        if (fromDate.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("fromDate cannot be before current date");
        if (toDate != null && fromDate.isAfter(toDate)) throw new IllegalArgumentException("fromDate cannot be after toDate");
        this.fromDate = fromDate;
    }

    private void setToDate(LocalDateTime toDate) {
        if (toDate == null) throw new IllegalArgumentException("toDate cannot be null");
        if (toDate.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("toDate cannot be before current date");
        if (fromDate != null && toDate.isBefore(fromDate)) throw new IllegalArgumentException("toDate cannot be before fromDate");
        this.toDate = toDate;
    }

    public void setIssuedDuring(Appointment issuedDuring) {
        if (issuedDuring == null) throw new IllegalArgumentException("Appointment cannot be null");
        if (issuedDuring.getStatus() != AppointmentStatus.COMPLETED)
            throw new UnsupportedOperationException("Appointment has not been conducted yet");
        this.issuedDuring = issuedDuring;
    }
}
