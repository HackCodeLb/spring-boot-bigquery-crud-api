package com.hackcode.demo.controller;

import com.hackcode.demo.model.Person;
import com.hackcode.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

  @Autowired
  private PersonService personService;

  private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

  @PostMapping
  public ResponseEntity<Void> createPerson(@RequestBody Person person) {
    try {
      personService.savePerson(person);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    catch (Exception e) {
      logger.error("Error creating person", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Person>> getAllPersons() {
    try {
      List<Person> persons = personService.getAllPersons();
      return ResponseEntity.ok(persons);
    } catch (Exception e) {
      logger.error("Error retrieving persons", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updatePerson(@PathVariable String id, @RequestBody Person person) {
    try {
      person.setId(id);
      personService.updatePerson(person);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
      logger.error("Error updating person", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePerson(@PathVariable String id) {
    try {
      personService.deletePerson(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      logger.error("Error deleting person", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
