package com.mercury.core.docs;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

/**
 * Spring configuration for OpenAPI documentation customization. Adds an {@code Accept-Language}
 * header to API operations, to specify the preferred response language.
 */
@Configuration
public class OpenApiLocalizationHeader {
  private static final String HEADER_NAME = "Accept-Language";

  /**
   * Provides an {@link OperationCustomizer} bean that adds the {@code Accept-Language} header to
   * OpenAPI operations.
   *
   * <p>This customizer adds the header to every operation. The added header is optional, with a
   * default value (currently "en") and an example list of supported languages (currently "en",
   * "de", "fr"). These language values are hardcoded. {@code @SuppressWarnings("unchecked")} is
   * used to suppress potential compiler warnings during schema creation for this header, related to
   * generics in the OpenAPI library.
   *
   * @return The {@link OperationCustomizer} bean for modifying OpenAPI operations.
   */
  @Bean
  public OperationCustomizer customizeAcceptLanguageHeader() {
    return (Operation operation, HandlerMethod handlerMethod) -> {
      Schema<String> langSchema = new Schema<>();
      langSchema.setType("string");
      langSchema.setDefault("en");
      langSchema.setEnum(List.of("en", "de", "fr"));

      Parameter acceptLanguageHeader =
          new Parameter()
              .in("header")
              .name(HEADER_NAME)
              .description("Preferred response language (e.g., 'en', 'de', 'fr').")
              .required(false)
              .schema(langSchema);

      operation.addParametersItem(acceptLanguageHeader);

      return operation;
    };
  }
}
