package com.mercury.core.docs;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info =
        @Info(
            title = "Mercury API",
            summary =
                "API for the Mercury platform, focusing on employee management, HR processes, and"
                    + " workflows.",
            description =
                """
                                ### Overview
                                Mercury API provides the backend services for the Mercury platform.
                                Currently, its primary focus is to support functionalities for **employee management** and the handling of related **HR processes and workflows**.
                                This includes managing employee data, controlling onboarding/offboarding, administering absences, and facilitating other HR operations.

                                ### Error Message Language
                                The language of error messages returned by the API can be controlled by the client using the `Accept-Language` HTTP header.
                                Supported languages include English (default, `en`), German (`de`), and French (`fr`).
                                If an unsupported language is requested, a key is missing for the requested language, or if the `Accept-Language` header is omitted, error messages will default to English.

                                ### Authentication
                                Most endpoints are secured using Bearer Token authentication (JWT).
                                A token can typically be obtained via an authentication endpoint (e.g., `/auth/login`).

                                ### Key Concepts (Current Focus)
                                * **Employee:** Represents an employee within the system (supersedes generic 'User' for core functions).
                                * **Workflow:** A defined HR process (e.g., leave request, performance review).
                                * **Action/Task:** A specific step within a workflow requiring interaction or completion.
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
                    url = "https://www.apache.org/licenses/LICENSE-2.html"), // Corrected URL
            extensions = {
              @Extension(
                  name = "x-logo",
                  properties = {
                    @ExtensionProperty(
                        name = "url",
                        value =
                            "https://raw.githubusercontent.com/sergej-stk/mercury/refs/heads/main/assets/logo.png",
                        parseValue = true),
                    @ExtensionProperty(
                        name = "altText",
                        value = "Mercury API Logo",
                        parseValue = true),
                    @ExtensionProperty(
                        name = "backgroundColor",
                        value = "#FFFFFF",
                        parseValue = true),
                    @ExtensionProperty(
                        name = "href",
                        value = "https://mercury.sergejsteinsiek.com/",
                        parseValue = true)
                  })
            }),
    servers = {
      @Server(url = "http://localhost:8080", description = "Development Server"),
      @Server(url = "https://api.mercury.example.com", description = "Production Server")
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
  private final OpenApiLocalizationHeader openApiLocalizationHeader;

  @Autowired
  public APIDefinition(OpenApiLocalizationHeader openApiLocalizationHeader) {
    this.openApiLocalizationHeader = openApiLocalizationHeader;
  }

  @Bean
  public GroupedOpenApi apiV1() {
    return GroupedOpenApi.builder()
        .group("v1")
        .pathsToMatch("/v1/**")
        .addOperationCustomizer(openApiLocalizationHeader.customizeAcceptLanguageHeader())
        .build();
  }
}
