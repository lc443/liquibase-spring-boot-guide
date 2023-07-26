package com.leron.liquibase.controller;

import com.leron.liquibase.models.Person;
import com.leron.liquibase.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping()
    public String createPerson(@RequestParam String name) {
        Person person = new Person();
        person.setFirstName(name);

        personRepository.save(person);
        return personRepository.findByName(name);
    }

    @GetMapping()
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
