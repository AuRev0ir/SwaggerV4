package ru.spring.app.rest.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.spring.app.domain.Avatar;
import ru.spring.app.rest.dto.AvatarDto;
import ru.spring.app.service.PersonService;
import java.io.IOException;
import java.util.List;


// Предположим у нас 2-ая Url версия (v2) х_X

@Tag(name = "Full Avatar API ", description = "Everything about avatars")
@RestController
public class AvatarController {

    private final PersonService service;

    @Autowired
    public AvatarController(PersonService service) {
        this.service = service;
    }


    @Tag(name = "Required API ", description = "Required for server operation")
    @Operation(summary = "Uploads an images")
    @ApiResponses(value = {
            @ApiResponse(
            description = "Successful operation",
            responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AvatarDto.class)
                    )
            }) ,
            @ApiResponse(responseCode = "400", description = "Client Error", content = {@Content(
                    schema =@Schema())}),
            @ApiResponse(responseCode = "500", description = "An error occurred on the server",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "415", description = "The server refuses to accept the request",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "403", description = "Access blocked",
                    content = {@Content(schema = @Schema())})
    })
    @PostMapping(value = "api/v2/images/uploadImage", consumes = {"multipart/form-data"})
    public AvatarDto imageUpload (@RequestPart(required = true) MultipartFile img) throws IOException {
        return service.imageUploading(img);
    }



    //Hidden метод использую в качестве тестирования х_Х

    @Tag(name = "Avatar API hidden", description = "Secret controller")
    //@Hidden
    @Operation(summary = "Get all avatars")
    @ApiResponses( value = {
            @ApiResponse(
            description = "Successful operation",
            responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema ( schema = @Schema(implementation = Avatar.class))
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Client Error",
                    content = {@Content(schema =@Schema())}),
            @ApiResponse(responseCode = "500", description = "An error occurred on the server",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "403", description = "Access blocked",
                    content = {@Content(schema = @Schema())}),
    }
    )
    @GetMapping("api/v2/images")
    public List<Avatar> getAllUrlImage(){
        return service.getAllUrlImage();
    }












}
