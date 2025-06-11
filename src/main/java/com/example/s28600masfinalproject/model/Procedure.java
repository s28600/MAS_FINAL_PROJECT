package com.example.s28600masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private static Map<ProcedureType, Double> proceduresCost = new HashMap<>();
    static {
        proceduresCost.put(ProcedureType.SURGICAL, 500d);
        proceduresCost.put(ProcedureType.DIAGNOSTIC, 200d);
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.PRIVATE)
    private ProcedureType procedureType;

    @OneToMany(mappedBy = "performed")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> performedDuring = new HashSet<>();

    public static Map<ProcedureType, Double> getProceduresCost() {
        return Collections.unmodifiableMap(proceduresCost);
    }

    public double getCost() {
        return proceduresCost.get(procedureType);
    }
}
