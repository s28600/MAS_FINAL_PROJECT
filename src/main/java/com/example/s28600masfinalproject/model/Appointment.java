package com.example.s28600masfinalproject.model;

import com.example.s28600masfinalproject.validator.ValidAppointment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@ValidAppointment
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Animal treatedAnimal;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Person appointedPerson;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Person conductedBy;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Procedure performed;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Consultation comprisedOf;

    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Administration> administrations = new HashSet<>();

    @OneToMany(mappedBy = "issuedDuring", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Prescription> issued;

    public Appointment(LocalDateTime date, AppointmentStatus status, Animal treatedAnimal, Person appointedPerson, Person conductedBy, Procedure performedProcedure) {
        setDate(date);
        setStatus(status);
        setTreatedAnimal(treatedAnimal);
        setAppointedPerson(appointedPerson);
        setConductedBy(conductedBy);
        setPerformed(performedProcedure);
    }

    public Appointment(LocalDateTime date, AppointmentStatus status, Animal treatedAnimal, Person appointedPerson, Person conductedBy, Consultation comprisedOfConsultation) {
        setDate(date);
        setStatus(status);
        setTreatedAnimal(treatedAnimal);
        setAppointedPerson(appointedPerson);
        setConductedBy(conductedBy);
        setComprisedOf(comprisedOfConsultation);
    }

    public void ChangeAppointmentStatus(AppointmentStatus appointmentStatus) {
        setStatus(appointmentStatus);
    }

    public double getCost(){
        double cost = 0;
        if (performed != null) cost += performed.getCost();
        if (comprisedOf != null) cost += Consultation.getCost();
        return cost;
    }
}
