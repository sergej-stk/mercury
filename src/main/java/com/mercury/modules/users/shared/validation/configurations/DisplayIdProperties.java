package com.mercury.modules.users.shared.validation.configurations;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "mercury.users.display-id")
@Getter
@Setter
@Validated
public class DisplayIdProperties {
  @NotEmpty private String prefix = "USR-";

  @Positive private int length = 8;

  @NotEmpty private String charset = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
}
