package com.springreactive.learning.repo;

import com.springreactive.learning.bean.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person, Integer> {

}
