package ru.spring.app.rest.dto;

import ru.spring.app.domain.Avatar;
import ru.spring.app.domain.Person;

import java.io.IOException;

public class PersonsDto {

    private String name;
    private String status;

    PersonsDto(){}

    public PersonsDto(String status, String name) {
        this.status = status;
        this.name = name;
    }

    public static PersonsDto toDto(Person person) {
        return new PersonsDto(person.getStatus(), person.getPersonName());
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }


}
