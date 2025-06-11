package com.example.s28600masfinalproject.repository;

import com.example.s28600masfinalproject.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByOrderByDateAsc();

    List<Appointment> findByDateOrderByDateAsc(Date date);

    default List<Appointment> findByDate(Date date){
        List<Appointment> out = findByDateOrderByDateAsc(date);
        return Collections.unmodifiableList(out);
    }
}
