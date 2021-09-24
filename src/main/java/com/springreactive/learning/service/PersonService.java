package com.springreactive.learning.service;

import com.springreactive.learning.bean.Person;
import com.springreactive.learning.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Flux<Person> getAllPersons(){
        return personRepository.findAll();
    }
    public Mono<Person> findPerson(int personId){
        return personRepository.findById(personId);
    }

    public Mono<Person> updatePerson(Person personTobeupdated) {
        return personRepository.save(personTobeupdated);
    }

    public Mono<Void> deleteperson(Person personToBeDeleted){
        return personRepository.delete(personToBeDeleted);
    }
}
