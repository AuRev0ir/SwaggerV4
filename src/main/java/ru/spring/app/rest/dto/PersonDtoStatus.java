package ru.spring.app.rest.dto;

import ru.spring.app.domain.Person;

public class PersonDtoStatus {

    private long id;
    private String newStatus;
    private String oldStatus;

    public PersonDtoStatus(){}

    public PersonDtoStatus(long id, String newStatus, String oldStatus) {
        this.id = id;
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    public static PersonDtoStatus toDto(Person person) {
        return new PersonDtoStatus(person.getPersonId(), person.getStatus(), person.getOldStatus());
    }

    public long getId() {
        return id;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public String getOldStatus() {
        return oldStatus;
    }
}
