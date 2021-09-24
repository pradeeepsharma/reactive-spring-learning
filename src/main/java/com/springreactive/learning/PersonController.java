package com.springreactive.learning;

import com.springreactive.learning.bean.Person;
import com.springreactive.learning.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Person> getAllPersons() {

        return personService.getAllPersons();
    }

    @GetMapping(value = "/persons/{personId}")
    public Mono<ResponseEntity<Person>> findPerson(@PathVariable int personId) {
        return personService.findPerson(personId)
                .map(person -> ResponseEntity.ok(person))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/persons")
    public Mono<ResponseEntity<Person>> create(@RequestBody Person person) {
        return personService.updatePerson(person)
                .map(savedPerson -> ResponseEntity.ok(savedPerson))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/persons/{personId}")
    public Mono updatePerson(@PathVariable int personId, @RequestBody Person person) {
        return personService.findPerson(personId)
                .flatMap(selectedPerson -> {
                    selectedPerson.setFirstName(person.getFirstName());
                    selectedPerson.setLastName(person.getLastName());
                    selectedPerson.setDesignation(person.getDesignation());
                    return personService.updatePerson(selectedPerson);
                })
                .map(updatedPerson -> ResponseEntity.ok(updatedPerson))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/persons/{id}")
    public Mono<ResponseEntity<Void>> deletePerson(@PathVariable(value = "id") int personId) {

        return personService.findPerson(personId)
                .flatMap(personToDelete ->
                        personService.deleteperson(personToDelete)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
