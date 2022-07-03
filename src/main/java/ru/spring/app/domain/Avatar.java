package ru.spring.app.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.List;

@Entity
public class Avatar {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long avatarId;

    @OneToMany(targetEntity = Person.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> persons;
    private String urlPicture;

    public Avatar(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public Avatar(){}

    public long getAvatarId() {
        return avatarId;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
