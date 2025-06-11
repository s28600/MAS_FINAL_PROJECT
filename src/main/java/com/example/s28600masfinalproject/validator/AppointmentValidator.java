package com.example.s28600masfinalproject.validator;

import com.example.s28600masfinalproject.model.Appointment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AppointmentValidator implements ConstraintValidator<ValidAppointment, Appointment> {
    @Override
    public void initialize(ValidAppointment constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Appointment appointment, ConstraintValidatorContext context) {
        if (appointment.getAppointedPerson() == appointment.getConductedBy()) return false;
        if (!(appointment.getTreatedAnimal().getOwnedBy() == appointment.getAppointedPerson())) return false;
        if (appointment.getComprisedOf() != null && appointment.getPerformed() != null) return false;
        if (appointment.getComprisedOf() == null && appointment.getPerformed() == null) return false;

        return true;
    }
}
