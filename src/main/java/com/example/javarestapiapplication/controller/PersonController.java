package com.example.javarestapiapplication.controller;

import com.example.javarestapiapplication.domain.Person;
import com.example.javarestapiapplication.dto.PersonDto;
import com.example.javarestapiapplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<PersonDto> getPerson(@RequestParam String birthNumber) {
        PersonDto personDto = personService.getPerson(birthNumber);
        return ResponseEntity.ok(personDto);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person createdPerson = personService.addPerson(person);
        return ResponseEntity.ok(createdPerson);
    }

    @DeleteMapping
    public ResponseEntity<Person> deletePerson(@RequestParam String birthNumber) {
        Person person = personService.deletePerson(birthNumber);
        return ResponseEntity.ok(person);
    }
}
