package com.example.s28600masfinalproject.controller;

import com.example.s28600masfinalproject.model.Person;
import com.example.s28600masfinalproject.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class PersonController {
    private final PersonRepository personRepository;

    @GetMapping
    public String showClientPage() {
        return "clients"; // loads the base page with button
    }

    @GetMapping("/all")
    public String getAllClients(Model model) {
        List<Person> clients = personRepository.findAllClients();
        model.addAttribute("clients", clients);
        return "clients :: clientList"; // return only fragment
    }

    @GetMapping("/{id}/appointments")
    public String showAppointments(@PathVariable Long id, Model model) {
        Person client = personRepository.findById(id).orElseThrow();
        model.addAttribute("appointments", client.getAppointedFor());
        return "clients :: appointmentList"; // return the appointment fragment only
    }
}
