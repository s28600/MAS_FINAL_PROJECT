package com.example.s28600masfinalproject.builder;

import com.example.s28600masfinalproject.model.Person;
import com.example.s28600masfinalproject.model.PersonType;

import java.util.Set;

public class PersonBuilder {
    private final String name;
    private final String surname;
    private final String pesel;
    private final Set<PersonType> roles;

    private String address;
    private String specialization;
    private int bookedAppointments;

    private PersonBuilder(String name, String surname, String pesel, Set<PersonType> roles) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        if (surname == null || surname.isBlank()) throw new IllegalArgumentException("Surname cannot be null or blank");
        if (pesel == null || !pesel.matches("\\d{11}")) throw new IllegalArgumentException("Pesel cannot be null or blank and must consist of 11 digits");
        if (roles == null || roles.isEmpty()) throw new IllegalArgumentException("Person has to have at least one role");

        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.roles = roles;
    }

    public static RoleSelector createPerson(String name, String surname, String pesel) {
        return new RoleSelector(name, surname, pesel);
    }

    public static class RoleSelector {
        private final String name;
        private final String surname;
        private final String pesel;

        private RoleSelector(String name, String surname, String pesel) {
            this.name = name;
            this.surname = surname;
            this.pesel = pesel;
        }

        public Builder withRoles(Set<PersonType> roles) {
            return new Builder(name, surname, pesel, roles);
        }
    }

    public static class Builder {
        private final PersonBuilder personBuilder;

        private Builder(String name, String surname, String pesel, Set<PersonType> roles) {
            this.personBuilder = new PersonBuilder(name, surname, pesel, roles);
        }

        public Builder address(String address) {
            if (!personBuilder.roles.contains(PersonType.CLIENT)) {
                throw new UnsupportedOperationException("Cannot set address: not a CLIENT");
            }
            if (address == null || address.isBlank()) {
                throw new IllegalArgumentException("Address cannot be null or blank");
            }
            personBuilder.address = address;
            return this;
        }

        public Builder specialization(String specialization) {
            if (!personBuilder.roles.contains(PersonType.DOCTOR)) {
                throw new UnsupportedOperationException("Cannot set specialization: not a DOCTOR");
            }
            if (specialization == null || specialization.isBlank()) {
                throw new IllegalArgumentException("Specialization cannot be null or blank");
            }
            personBuilder.specialization = specialization;
            return this;
        }

        public Builder bookedAppointments(int count) {
            if (!personBuilder.roles.contains(PersonType.RECEPTIONIST)) {
                throw new UnsupportedOperationException("Cannot set booked appointments: not a RECEPTIONIST");
            }
            if (count < 0) {
                throw new IllegalArgumentException("Booked appointments cannot be negative");
            }
            personBuilder.bookedAppointments = count;
            return this;
        }

        public Person build() {
            Person p = new Person();
            p.setName(personBuilder.name);
            p.setSurname(personBuilder.surname);
            p.setPesel(personBuilder.pesel);
            p.setPersonType(personBuilder.roles);
            if (personBuilder.roles.contains(PersonType.CLIENT)) p.setAddress(personBuilder.address);
            if (personBuilder.roles.contains(PersonType.DOCTOR)) p.setSpecialization(personBuilder.specialization);
            if (personBuilder.roles.contains(PersonType.RECEPTIONIST)) p.setBookedAppointments(personBuilder.bookedAppointments);
            return p;
        }
    }
}
