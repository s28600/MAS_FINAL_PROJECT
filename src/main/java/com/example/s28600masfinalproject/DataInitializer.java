package com.example.s28600masfinalproject;

import com.example.s28600masfinalproject.builder.PersonBuilder;
import com.example.s28600masfinalproject.model.*;
import com.example.s28600masfinalproject.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import static com.example.s28600masfinalproject.util.PeselGenerator.generateRandomPesel;


@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final ProcedureRepository procedureRepository;
    private final ConsultationRepository consultationRepository;
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final CatRepository catRepository;
    private final DogRepository dogRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicationRepository medicationRepository;
    private final AdministrationRepository administrationRepository;
    private final PrescriptionRepository prescriptionRepository;

    private Procedure procedure1, procedure2;
    private Consultation consultation1, consultation2, consultation3, consultation4;
    private Person person1, person2, person3, person4, person5, person6, person7;
    private Cat cat1, cat2, cat3, cat4, cat5, cat6;
    private Dog dog1, dog2, dog3;
    private Appointment appointment1, appointment2, appointment3, appointment4, appointment5, appointment6;
    private Medication medication1, medication2, medication3, medication4;
    private Administration administration1, administration2;
    private Prescription prescription1, prescription2;

    @EventListener
    public void atStart(ContextRefreshedEvent event){
        System.out.println("========== [Checking person repository for present data] ==========");
        if (personRepository.count() == 0){
            System.out.println("Person repository empty - populating data...");
            person1 = PersonBuilder.createPerson("Emilia", "Kowalska", generateRandomPesel())
                    .withRoles(Set.of(PersonType.CLIENT)).address("ul. Dębowa 123, 30-001 Kraków").build();
            person2 = PersonBuilder.createPerson("Jakub", "Nowak", generateRandomPesel())
                            .withRoles(Set.of(PersonType.DOCTOR)).specialization("Kardiologia").build();
            person3 = PersonBuilder.createPerson("Sara", "Wiśniewska", generateRandomPesel())
                            .withRoles(Set.of(PersonType.RECEPTIONIST)).bookedAppointments(24).build();
            person4 = PersonBuilder.createPerson("Michał", "Królik", generateRandomPesel())
                            .withRoles(Set.of(PersonType.CLIENT, PersonType.DOCTOR, PersonType.RECEPTIONIST))
                            .address("ul. Wierzba 12, 60-001 Poznań").specialization("Kardiologia").bookedAppointments(2).build();
            person5 = PersonBuilder.createPerson("Oliwia", "Nowakowska", generateRandomPesel())
                    .withRoles(Set.of(PersonType.CLIENT, PersonType.DOCTOR))
                    .address("ul. Cedrowa 56, 80-001 Gdańsk").specialization("Dermatologia").build();
            person6 = PersonBuilder.createPerson("Daniel", "Majewski", generateRandomPesel())
                    .withRoles(Set.of(PersonType.DOCTOR, PersonType.RECEPTIONIST))
                    .specialization("Neurologia").bookedAppointments(59).build();
            person7 = PersonBuilder.createPerson("Liliana", "Zawisza", generateRandomPesel())
                    .withRoles(Set.of(PersonType.CLIENT, PersonType.RECEPTIONIST))
                    .address("ul. Wiązowa 120, 40-001 Katowice").bookedAppointments(90).build();
            personRepository.saveAll(Arrays.asList(person1, person2, person3, person4, person5, person6, person7));
            System.out.println("Person repository populated successfully");
        } else {
            System.out.println("Person repository read: " + personRepository.count() + " instances");
        }
        System.out.println("========== [Checking animal repository for present data] ==========");
        if (animalRepository.count() == 0){
            System.out.println("Animal repository empty - populating data...");
            cat1 = new Cat("Mruczek", person1, 3, "Syjamski", Gender.MALE, true, "krótka");
            cat2 = new Cat("Kicia", person1, 2, Gender.FEMALE, false, "krótka");
            cat3 = new Cat("Filemon", person1, 5, "Maine Coon", Gender.MALE, true, "długa");
            cat4 = new Cat("Łatka", person1, 1, Gender.FEMALE, false, "średnia");
            cat5 = new Cat("Bonifacy", person5, 4, "Brytyjski Krótkowłosy", Gender.MALE, true, "krótka");
            cat6 = new Cat("Psotka", person5, 6, "Perski", Gender.FEMALE, true, "długa");
            dog1 = new Dog("Burek", person1, 4, "Owczarek Niemiecki", Gender.MALE, true, "Psy pasterskie");
            dog2 = new Dog("Saba", person5, 2, Gender.FEMALE, false, "Psy ozdobne");
            dog3 = new Dog("Azor", person7, 5, "Labrador Retriever", Gender.MALE, true, "Psy aportujące");
            animalRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, dog1, dog2, dog3));
            System.out.println("Animal repository populated successfully");
        } else {
            System.out.println("Animal repository read: " + animalRepository.count() + " instances");
        }
        System.out.println("========== [Checking procedure repository for present data] ==========");
        if (procedureRepository.count() == 0){
            System.out.println("Procedure repository empty - populating data...");
            procedure1 = Procedure.builder().procedureType(ProcedureType.DIAGNOSTIC).build();
            procedure2 = Procedure.builder().procedureType(ProcedureType.SURGICAL).build();
            procedureRepository.saveAll(Arrays.asList(procedure1, procedure2));
            System.out.println("Procedure repository populated successfully");
        } else {
            System.out.println("Procedure repository read: " + procedureRepository.count() + " instances");
        }
        System.out.println("========== [Checking consultation repository for present data] ==========");
        if (consultationRepository.count() == 0){
            System.out.println("Consultation repository empty - populating data...");
            consultation1 = Consultation.builder().description("Nefrologiczna konsultacja").build();
            consultation2 = Consultation.builder().description("Internistyczna konsultacja").build();
            consultation3 = Consultation.builder().description("Kardiologiczna konsultacja").build();
            consultation4 = Consultation.builder().description("Dermatologiczna konsultacja").build();
            consultationRepository.saveAll(Arrays.asList(consultation1, consultation2, consultation3, consultation4));
            System.out.println("Consultation repository populated successfully");
        } else {
            System.out.println("Consultation repository read: " + consultationRepository.count() + " instances");
        }
        System.out.println("========== [Checking appointment repository for present data] ==========");
        if (appointmentRepository.count() == 0){
            System.out.println("Appointment repository empty - populating data...");
            appointment1 = new Appointment(LocalDateTime.now(), AppointmentStatus.COMPLETED, cat1, person1, person5, procedure1);
            appointment2 = new Appointment(LocalDateTime.now(), AppointmentStatus.COMPLETED, cat2, person1, person6, procedure2);
            appointment3 = new Appointment(LocalDateTime.now(), AppointmentStatus.PLANNED, cat3, person1, person6, procedure2);
            appointment4 = new Appointment(LocalDateTime.now(), AppointmentStatus.PLANNED, cat5, person5, person2, consultation1);
            appointment5 = new Appointment(LocalDateTime.now(), AppointmentStatus.PLANNED, dog1, person1, person2, consultation2);
            appointment6 = new Appointment(LocalDateTime.now(), AppointmentStatus.PLANNED, dog3, person7, person6, procedure1);
            appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2, appointment3, appointment4, appointment5, appointment6));
            System.out.println("Appointment repository populated successfully");
        } else {
            System.out.println("Appointment repository read: " + appointmentRepository.count() + " instances");
        }
        System.out.println("========== [Checking medication repository for present data] ==========");
        if (medicationRepository.count() == 0){
            System.out.println("Medication repository empty - populating data...");
            medication1 = Medication.builder().name("Amoxil 500mg").costPerUnit(4.45).build();
            medication2 = Medication.builder().name("Lipitor 10mg").costPerUnit(2.30).build();
            medication3 = Medication.builder().name("Xanax 0.5mg").costPerUnit(1.80).build();
            medication4 = Medication.builder().name("Aspirin 325mg").costPerUnit(9.10).build();
            medicationRepository.saveAll(Arrays.asList(medication1, medication2, medication3, medication4));
            System.out.println("Medication repository populated successfully");
        } else {
            System.out.println("Medication repository read: " + medicationRepository.count() + " instances");
        }
        System.out.println("========== [Checking administration repository for present data] ==========");
        if (administrationRepository.count() == 0){
            System.out.println("Administration repository empty - populating data...");
            administration1 = Administration.builder().dosageInUnits(2).route(AdministrationRoute.INJECTION).appointment(appointment1).medication(medication1).build();
            administration2 = Administration.builder().dosageInUnits(5).route(AdministrationRoute.TOPICAL).appointment(appointment2).medication(medication2).build();
            administrationRepository.saveAll(Arrays.asList(administration1, administration2));
            System.out.println("Administration repository populated successfully");
        } else {
            System.out.println("Administration repository read: " + administrationRepository.count() + " instances");
        }
        System.out.println("========== [Checking prescription repository for present data] ==========");
        if (prescriptionRepository.count() == 0){
            System.out.println("Prescription repository empty - populating data...");
            prescription1 = Prescription.builder()
                    .fromDate(LocalDateTime.now())
                    .toDate(LocalDateTime.now().plusDays(5))
                    .code("code1").issuedDuring(appointment1)
                    .prescribed(Set.of(medication1, medication2))
                    .build();
            prescription2 = Prescription.builder()
                    .fromDate(LocalDateTime.now())
                    .toDate(LocalDateTime.now().plusDays(20))
                    .code("code2").issuedDuring(appointment2)
                    .prescribed(Set.of(medication3, medication4))
                    .build();
            prescriptionRepository.saveAll(Arrays.asList(prescription1, prescription2));
            System.out.println("Prescription repository populated successfully");
        } else {
            System.out.println("Prescription repository read: " + prescriptionRepository.count() + " instances");
        }
    }
}
