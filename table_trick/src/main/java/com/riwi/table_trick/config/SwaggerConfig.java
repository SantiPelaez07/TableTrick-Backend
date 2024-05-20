package com.riwi.table_trick.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "TableTrick",
                description = "Sistema de reservas para restaurante",
                version = "1.0.0",
                contact = @Contact(
                        name = "Table trick",
                        url = "tabletrick.com",
                        email = "tabletrick@gmail.com"
                )
        ),

        security = @SecurityRequirement(
                name ="Security token"
        )


)
@SecurityScheme(
        name = "Security token" ,
        description = "Access token for my api",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer"
)
public class SwaggerConfig {

}
