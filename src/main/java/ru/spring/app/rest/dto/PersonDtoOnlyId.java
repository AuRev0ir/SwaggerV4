package ru.spring.app.rest.dto;

import ru.spring.app.domain.Person;

public class PersonDtoOnlyId {


    private long id;

    public PersonDtoOnlyId(){}

    public PersonDtoOnlyId(long id) {
        this.id = id;
    }
    public static PersonDtoOnlyId toDto(Person person) {
        return new PersonDtoOnlyId(person.getPersonId());
    }

    public long getId() {
        return id;
    }
}
