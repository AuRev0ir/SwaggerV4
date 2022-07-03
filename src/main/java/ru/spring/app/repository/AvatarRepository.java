package ru.spring.app.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.app.domain.Avatar;
import ru.spring.app.domain.Person;

import java.util.List;

public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    List<Avatar> findAll();
}
