package ru.spring.app.rest.errorHandler;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.spring.app.rest.exception.*;

import java.io.IOException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFound(NotFoundException ex){
        return ResponseEntity.badRequest().body("Person not found");
    }

    @ExceptionHandler(NoStatusException.class)
    public ResponseEntity<String> handlerNoStatus(NoStatusException ex){
        return ResponseEntity.badRequest().body("This status does not exist\n" +
                "Select status (online or offline)");
    }

    @ExceptionHandler(NotFoundUrlImageException.class)
    public ResponseEntity<String> handlerNotFound (NotFoundUrlImageException ex){
        return ResponseEntity.badRequest().body("Url Image missing");
    }

    @ExceptionHandler(UrlRepetitionException.class)
    public ResponseEntity<String> handlerUrl (UrlRepetitionException ex){
        return ResponseEntity.badRequest().body("Such a URL exists\n" +
                "Please change url");
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handlerIOExceptio (IOException ex){
        return ResponseEntity.badRequest().body("Image not found");
    }

    @ExceptionHandler(NoImageUrlException.class)
    public ResponseEntity<String> handlerNoUrl (NoImageUrlException ex){
        return ResponseEntity.badRequest().body("There are no Url images in the database");
    }
}
