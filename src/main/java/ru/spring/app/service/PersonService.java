package ru.spring.app.service;

import org.springframework.web.multipart.MultipartFile;
import ru.spring.app.domain.Avatar;
import ru.spring.app.domain.Person;
import ru.spring.app.rest.dto.*;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    List<PersonsDto> getAllPersonStatus(String status);

    List<PersonsDto> getAllPersons();

    PersonDto getPersonById (long id);

    PersonDtoOnlyId addPerson(Person person);

    PersonDtoStatus updateStatus(long i, String status);

    AvatarDto imageUploading(MultipartFile img) throws IOException;

    List<Avatar> getAllUrlImage();

}
