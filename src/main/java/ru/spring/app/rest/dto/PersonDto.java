package ru.spring.app.rest.dto;

import ru.spring.app.domain.Person;

public class PersonDto {

    private long id;
    private String url;
    private String personName;
    private String email;
    private String status;

    PersonDto(){}

    public PersonDto(long id, String personName, String status, String email, String url) {
        this.id = id;
        this.personName = personName;
        this.status = status;
        this.email = email;
        this.url = url;
    }

    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getPersonId(), person.getPersonName(),
                person.getStatus(), person.getEmail(), person.getUrl());
    }

    public String getUrl() {
        return url;
    }

    public long getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }
}
