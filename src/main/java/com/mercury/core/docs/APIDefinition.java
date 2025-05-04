package com.mercury.core.docs;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =
                @Info(
                        title = "Mercury API",
                        summary = "General-purpose API framework for future application features.",
                        description =
                                """
                                ### Overview
                                Mercury is a flexible backend platform currently under development.
                                It is designed to provide a foundation for various functionalities such as user management,
                                data exchange, and secure service interaction.

                                ### Authentication
                                Most endpoints are secured using Bearer Token authentication (JWT).
                                A token can typically be obtained via an authentication endpoint (e.g., `/auth/login`).

                                ### Key Concepts
                                * **User:** A general representation of an authenticated entity.
                                * **Entity:** Placeholder for domain-specific resources.
                                * **Operation:** Placeholder for interactions with data or processes.
                                """,
                        version = "1.0.0",
                        termsOfService = "https://example.com/terms",
                        contact =
                                @Contact(
                                        name = "Mercury Dev Team",
                                        url = "https://github.com/sergej-stk/mercury",
                                        email = "mercury-dev@example.com"),
                        license =
                                @License(
                                        name = "Apache 2.0",
                                        url = "https://www.apache.org/licenses/LICENSE-2.0.html")),
        servers = {
            @Server(url = "http://localhost:8080", description = "Development Server"),
            @Server(url = "https://api.mercury.example.com", description = "Production Server")
        },
        tags = {
            @Tag(
                    name = "Authentication",
                    description = "Authentication and token management.",
                externalDocs = @ExternalDocumentation(
                        description = "Find out more",
                        url = "https://github.com/sergej-stk/mercury")
            ),
            @Tag(name = "Users", description = "Operations related to user accounts.",                 externalDocs = @ExternalDocumentation(
                    description = "Find out more",
                    url = "https://github.com/sergej-stk/mercury")),
            @Tag(name = "Core", description = "General-purpose endpoints and features.",                 externalDocs = @ExternalDocumentation(
                    description = "Find out more",
                    url = "https://github.com/sergej-stk/mercury"))
        },
        externalDocs =
                @ExternalDocumentation(
                        description = "GitHub Repository",
                        url = "https://github.com/sergej-stk/mercury"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER,
        description =
                "Bearer Authentication with JWT. Include the token in the 'Authorization' header as"
                        + " 'Bearer {token}'.",
        paramName = "Authorization")
public class APIDefinition {
    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder().group("v1").pathsToMatch("/v1/**").build();
    }
}
