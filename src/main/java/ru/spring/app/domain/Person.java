package ru.spring.app.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class Person {


    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;

    private String url;
    private String personName;

    private String email;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String status = "online";
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String oldStatus;

    public Person(String personName, String email, String url) {
        this.personName = personName;
        this.email = email;
        this.url = url;
    }
    public Person() {}


    public String getUrl() {
        return url;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getPersonId() {
        return personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
