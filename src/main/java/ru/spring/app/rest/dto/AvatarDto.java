package ru.spring.app.rest.dto;

import ru.spring.app.domain.Avatar;

public class AvatarDto {

    private String urlPicture;

    AvatarDto(){}

    public AvatarDto(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public static AvatarDto toDto(Avatar avatar) {
        return new AvatarDto(avatar.getUrlPicture());
    }

    public String getUrlPicture() {
        return urlPicture;
    }
}
