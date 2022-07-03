package ru.spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.spring.app.domain.Avatar;
import ru.spring.app.domain.Person;
import ru.spring.app.repository.AvatarRepository;
import ru.spring.app.repository.PersonRepository;
import ru.spring.app.rest.dto.*;
import ru.spring.app.rest.exception.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private final PersonRepository repository;
    private final AvatarRepository repositoryUrl;

    public PersonServiceImpl(PersonRepository repository, AvatarRepository repositoryUrl) {
        this.repository = repository;
        this.repositoryUrl = repositoryUrl;
    }

    @Override
    public List<PersonsDto> getAllPersons() {
        List<Person> persons = repository.findAll();
        if(persons.isEmpty()){
            throw new NotFoundException();
        }
        return repository.findAll().stream().
                map(PersonsDto::toDto).collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(long id) {
        Person personById = repository.findById(id).orElseThrow(NotFoundException::new);
        return PersonDto.toDto(personById);
    }

    @Override
    public PersonDtoOnlyId addPerson(Person person)throws NotFoundException {
        boolean flagPermissions = false;
        List<Avatar> avatars = repositoryUrl.findAll();

        if (avatars.isEmpty()) {
            throw new NoImageUrlException();
        }

        for (Avatar avatar : avatars) {
            if (Objects.equals(avatar.getUrlPicture(), person.getUrl())){
                flagPermissions = true;
            }
        }
        if (!flagPermissions) {
            throw new NotFoundUrlImageException();
        }
        Person personSave = repository.save(person);
        return PersonDtoOnlyId.toDto(personSave);
    }

    @Override
    public PersonDtoStatus updateStatus(long id, String status) {
        Person updatePerson = repository.findById(id).orElseThrow(NotFoundException::new);
        if (Objects.equals(status, "offline") || Objects.equals(status, "online")){
            updatePerson.setOldStatus(updatePerson.getStatus());
            updatePerson.setStatus(status);
        } else {
            throw new NoStatusException();
        }
        repository.save(updatePerson);
        return PersonDtoStatus.toDto(updatePerson);
    }

    @Override
    public List<PersonsDto> getAllPersonStatus(String status) {
        List<Person> personList = repository.findAll();
        List<Person> filteredPersons;
        if (Objects.equals(status, "offline") || Objects.equals(status, "online")){
            filteredPersons = repository.findAll().stream().
                    filter(persons -> Objects.equals(persons.getStatus(), status)).
                    collect(Collectors.toList());
        } else {
            throw new NoStatusException();
        }
        if (personList.isEmpty()){
            throw new NotFoundException();
        }
        return filteredPersons.stream().
                map(PersonsDto::toDto).collect(Collectors.toList());

    }

    @Override
    public AvatarDto imageUploading(MultipartFile img) throws IOException {
        boolean flagPermissions = false;
        File convertImg = new File("src/main/webapp/uploads" + img.getOriginalFilename());
        convertImg.createNewFile();
        FileOutputStream imgOut = new FileOutputStream(convertImg);
        imgOut.write(img.getBytes());
        Avatar newAvatar = new Avatar(convertImg.getName());
        List<Avatar> avatars = repositoryUrl.findAll();
        avatars.stream().map(Avatar::getUrlPicture).collect(Collectors.toList());
        for (Avatar avatar : avatars) {
            if (Objects.equals(avatar.getUrlPicture(), newAvatar.getUrlPicture())){
                flagPermissions = true;
            }
        }
        if (flagPermissions){
            throw new UrlRepetitionException();
        }
        repositoryUrl.save(newAvatar);
        return AvatarDto.toDto(newAvatar);
    }

    @Override
    public List<Avatar> getAllUrlImage() {
        List<Avatar> avatars = repositoryUrl.findAll();
        if (avatars.isEmpty()){
            throw new NotFoundUrlImageException();
        }
        return repositoryUrl.findAll();
    }



}
