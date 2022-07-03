package ru.spring.app.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.app.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findAll();

}
