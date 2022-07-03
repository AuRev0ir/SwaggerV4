package ru.spring.app.configApp;


;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI myOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Person and Avatar API"). license(new License().name("Apache 2.0"))
                .version("2.0.0")
                .description("Test swagger")
                .contact(new Contact()
                        .name("Danil")
                        .email("myEmail@email.ru")
                        .url("https://myWebsite.ru")
                ))
                .servers(List.of(
                        new Server().url("http://localhost:8080")
                        .description("Main server"),
                        new Server().url("http://localhost:8081")
                        .description("Spare server"))
                );

    }
}
