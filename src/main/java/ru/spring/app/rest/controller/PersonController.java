package ru.spring.app.rest.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spring.app.domain.Person;
import ru.spring.app.rest.dto.PersonDto;
import ru.spring.app.rest.dto.PersonDtoOnlyId;
import ru.spring.app.rest.dto.PersonDtoStatus;
import ru.spring.app.rest.dto.PersonsDto;
import ru.spring.app.service.PersonService;

import java.util.List;

// Предположим у нас 2-ая Url версия (v2) х_X

@Tag(name = "Full Person API", description = "Everything about persons")
@RestController
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }


    @Tag(name = "Required API ", description = "Required for server operation")
    @Operation(summary = "Create person")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonDtoOnlyId.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "An error occurred on the server",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Client Error",
                    content = {@Content(schema =@Schema())}),
            @ApiResponse(responseCode = "403", description = "Access blocked",
                    content = {@Content(schema = @Schema())})
    })
    @PostMapping(value = "api/v2/persons/newPerson", consumes = {"application/json"})
    public PersonDtoOnlyId addPerson(@RequestBody(required = true) Person newPerson) {
        return service.addPerson(newPerson);
    }



    @Operation(summary = "Find person by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonDto.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "An error occurred on the server",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Client Error",
                    content = {@Content(schema =@Schema())}),
            @ApiResponse(responseCode = "403", description = "Access blocked",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/api/v2/persons/{personId}")
    public PersonDto getUserById (@PathVariable("personId") Long id) {
        return service.getPersonById(id);
    }



    @Operation(summary = "Update status person by ID")
    @Parameter(name = "status" , required = true, content = {
            @Content(schema = @Schema(type = "string"))
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonDtoStatus.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "An error occurred on the server",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Client Error",
                    content = {@Content(schema =@Schema())}),
            @ApiResponse(responseCode = "403", description = "Access blocked",
                    content = {@Content(schema = @Schema())})
    })
    @PatchMapping ("/api/v2/persons/{personId}")
    public PersonDtoStatus updateStatusOnOnline (@PathVariable ("personId") Long id,
                                                 @RequestParam ("status") String status ){
        return service.updateStatus(id, status);
    }


    @Operation(summary = "Get all persons")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema( schema = @Schema(implementation = PersonsDto.class))
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "An error occurred on the server",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Client Error",
                    content = {@Content(schema =@Schema())}),
            @ApiResponse(responseCode = "403", description = "Access blocked",
                    content = {@Content(schema = @Schema())})
    }
    )
    @GetMapping("/api/v2/persons")
    public List<PersonsDto> getAllPerson() {
        return service.getAllPersons();
    }


    @Operation(summary = "Get all persons by status")
    @Parameter(name = "status", required = true, content = {
            @Content(schema = @Schema(type = "string"))
    })
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema( schema = @Schema(implementation = PersonsDto.class))
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "An error occurred on the server",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Client Error",
                    content = {@Content(schema =@Schema())})
    }
    )
    @GetMapping("/api/v2/persons/findByStatus")
    public List<PersonsDto> getAllPersonsOnOnline(@RequestParam ("status") String status) {
        return service.getAllPersonStatus(status);
    }

}
