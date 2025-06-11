package com.example.s28600masfinalproject.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AppointmentValidator.class)
public @interface ValidAppointment {
    String message() default "Invalid Appointment";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
